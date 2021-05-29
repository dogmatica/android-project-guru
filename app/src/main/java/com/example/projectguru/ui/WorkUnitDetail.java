package com.example.projectguru.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;
import com.example.projectguru.data.Resource;
import com.example.projectguru.data.WorkUnit;
import com.example.projectguru.tools.AlertReceiver;
import com.example.projectguru.tools.Converters;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WorkUnitDetail extends AppCompatActivity {

    public static String LOG_TAG = "WorkUnitDetailActivityLog";
    MainDatabase db;
    TextView workUnitTitleTextView;
    TextView workUnitStartTextView;
    TextView workUnitEndTextView;
    TextView workUnitStatusTextView;
    FloatingActionButton editWorkUnitButton;
    ListView workUnitResourceListView;
    FloatingActionButton addWorkUnitResourceButton;
    FloatingActionButton deleteWorkUnitButton;
    Button button2;
    Intent intent;
    int workUnitId;
    int phaseId;
    int projectId;
    WorkUnit selectedWorkUnit;
    Phase selectedPhase;
    SimpleDateFormat formatter;

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
        updateViews();
    }

    //Inflation of hidden menu on action bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_alert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //The hidden menu in the CourseDetail view provides additional options:
        switch (item.getItemId()) {
            //When "Home" is selected:
            case R.id.home:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                return true;
            //When Alert is selected:
            case R.id.subitem1:
                //A unique value for the request code is generated based on the current time in milliseconds
                int requestCode = (int) System.currentTimeMillis();
                Date alertDate = selectedWorkUnit.getWorkUnit_start();
                String tempAlertDate = formatter.format(alertDate);
                Intent intent2 = new Intent(this, AlertReceiver.class);
                intent2.putExtra("title", selectedWorkUnit.getWorkUnit_title());
                intent2.putExtra("message", "Start date for " + selectedWorkUnit.getWorkUnit_title() + " has arrived: " + tempAlertDate);
                PendingIntent sender = PendingIntent.getBroadcast(this, requestCode, intent2, 0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, Converters.dateToTimestamp(alertDate), sender);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_unit_detail);
        setTitle("Work Unit Details");
        workUnitTitleTextView = findViewById(R.id.workUnitTitleTextView);
        workUnitStartTextView = findViewById(R.id.workUnitStartTextView);
        workUnitEndTextView = findViewById(R.id.workUnitEndTextView);
        workUnitStatusTextView = findViewById(R.id.workUnitStatusTextView);
        workUnitResourceListView = findViewById(R.id.workUnitResourceListView);
        button2 = findViewById(R.id.button2);
        editWorkUnitButton = findViewById(R.id.editWorkUnitButton);
        addWorkUnitResourceButton = findViewById(R.id.addWorkUnitResourceButton);
        deleteWorkUnitButton = findViewById(R.id.deleteWorkUnitButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        workUnitId = intent.getIntExtra("workUnitId", -1);
        selectedPhase = db.phaseDao().getPhase(projectId, phaseId);
        selectedWorkUnit = db.workUnitDao().getWorkUnit(phaseId, workUnitId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        //Query the database and update current layout with appropriate data:

        updateViews();
        updateList();

        editWorkUnitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkUnitEdit.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                startActivity(intent);
            }
        });

        addWorkUnitResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResourceSearch.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                startActivity(intent);
            }
        });

        deleteWorkUnitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(WorkUnitDetail.this).setTitle("Confirm").setMessage("Delete Work Unit?")
                        .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String workUnitName = selectedWorkUnit.getWorkUnit_title();
                        db.workUnitDao().deleteWorkUnit(workUnitId);
                        Toast.makeText(getApplicationContext(), "Work Unit " + workUnitName + " was deleted.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), PhaseDetail.class);
                        intent.putExtra("projectId", projectId);
                        intent.putExtra("phaseId", phaseId);
                        startActivity(intent);
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkUnitNote.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                startActivity(intent);
            }
        });


    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedWorkUnit != null) {
            Log.d(PhaseDetail.LOG_TAG, "selected WorkUnit is not null");
            Date startDate = selectedWorkUnit.getWorkUnit_start();
            Date endDate = selectedWorkUnit.getWorkUnit_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            workUnitStartTextView.setText(tempStart);
            workUnitEndTextView.setText(tempEnd);
            workUnitTitleTextView.setText(selectedWorkUnit.getWorkUnit_title());
            workUnitStatusTextView.setText(selectedWorkUnit.getWorkUnit_status());
        } else {
            Log.d(PhaseDetail.LOG_TAG, "selected WorkUnit is null");
            selectedWorkUnit = new WorkUnit();
        }
    }

    //Query the database and update current layout with appropriate data:

    private void updateList() {
        List<Resource> allWorkUnitResources = db.resourceDao().getAllWorkUnitResources(selectedWorkUnit.getWorkUnit_id());
        String[] items = new String[allWorkUnitResources.size()];
        if(!allWorkUnitResources.isEmpty()) {
            for (int i = 0; i < allWorkUnitResources.size(); i++) {
                items[i] = allWorkUnitResources.get(i).getResource_name();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        workUnitResourceListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}