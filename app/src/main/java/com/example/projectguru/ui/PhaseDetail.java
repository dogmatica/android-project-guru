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
import android.widget.AdapterView;
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
import com.example.projectguru.data.Project;
import com.example.projectguru.data.WorkUnit;
import com.example.projectguru.tools.AlertReceiver;
import com.example.projectguru.tools.Converters;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PhaseDetail extends AppCompatActivity {

    public static String LOG_TAG = "PhaseDetailActivityLog";
    MainDatabase db;
    TextView phaseTitleTextView;
    TextView phaseStartTextView;
    TextView phaseEndTextView;
    TextView phaseStatusTextView;
    FloatingActionButton editPhaseButton;
    ListView workUnitList;
    FloatingActionButton addPhaseWorkUnitButton;
    FloatingActionButton deletePhaseButton;
    Button button2;
    Intent intent;
    int phaseId;
    int projectId;
    Project selectedProject;
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
                Date alertDate = selectedPhase.getPhase_start();
                String tempAlertDate = formatter.format(alertDate);
                Intent intent2 = new Intent(this, AlertReceiver.class);
                intent2.putExtra("title", selectedPhase.getPhase_name());
                intent2.putExtra("message", "Start date for " + selectedPhase.getPhase_name() + " has arrived: " + tempAlertDate);
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
        setContentView(R.layout.activity_phase_detail);
        setTitle("Phase Details");
        phaseTitleTextView = findViewById(R.id.phaseTitleTextView);
        phaseStartTextView = findViewById(R.id.phaseStartTextView);
        phaseEndTextView = findViewById(R.id.phaseEndTextView);
        phaseStatusTextView = findViewById(R.id.phaseStatusTextView);
        workUnitList = findViewById(R.id.workUnitList);
        button2 = findViewById(R.id.button2);
        editPhaseButton = findViewById(R.id.editPhaseButton);
        addPhaseWorkUnitButton = findViewById(R.id.addPhaseWorkUnitButton);
        deletePhaseButton = findViewById(R.id.deletePhaseButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        phaseId = intent.getIntExtra("phaseId", -1);
        projectId = intent.getIntExtra("projectId", -1);
        selectedPhase = db.phaseDao().getPhase(projectId, phaseId);
        selectedProject = db.projectDao().getProject(projectId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        //Query the database and update current layout with appropriate data:

        updateViews();
        updateList();

        workUnitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Loading the term detail view, passing variable termId:
                Intent intent = new Intent(getApplicationContext(), WorkUnitDetail.class);
                int workUnitId;
                List<WorkUnit> workUnitsList = db.workUnitDao().getPhaseWorkUnitList(phaseId);
                workUnitId = workUnitsList.get(position).getWorkUnit_id();
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                startActivity(intent);
            }
        });

        editPhaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhaseEdit.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                startActivity(intent);
            }
        });

        addPhaseWorkUnitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WorkUnitEdit.class);
                Calendar calendar = Calendar.getInstance();
                int dbCount = db.workUnitDao().getAllWorkUnits().size() + 1;
                WorkUnit tempWorkUnit = new WorkUnit();
                String tempWorkUnitName = "New Work Unit " + dbCount;
                tempWorkUnit.setWorkUnit_title(tempWorkUnitName);
                tempWorkUnit.setWorkUnit_start(calendar.getTime());
                tempWorkUnit.setWorkUnit_end(calendar.getTime());
                tempWorkUnit.setWorkUnit_status("Planned");
                tempWorkUnit.setWorkUnit_notes("Notes go here.");
                tempWorkUnit.setPhase_id_fk(phaseId);
                db.workUnitDao().insertWorkUnit(tempWorkUnit);
                tempWorkUnit = db.workUnitDao().getWorkUnitByTitle(tempWorkUnitName);
                int workUnitId = tempWorkUnit.getWorkUnit_id();
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                startActivity(intent);
            }
        });

        deletePhaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(PhaseDetail.this).setTitle("Confirm").setMessage("Delete Phase?")
                        .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String phaseName = selectedPhase.getPhase_name();
                        db.phaseDao().deletePhase(phaseId);
                        Toast.makeText(getApplicationContext(), "Phase " + phaseName + " was deleted.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ProjectDetail.class);
                        intent.putExtra("projectId", projectId);
                        startActivity(intent);
                    }
                });
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhaseNote.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                startActivity(intent);
            }
        });
    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedPhase != null) {
            Log.d(PhaseDetail.LOG_TAG, "selected Phase is not null");
            Date startDate = selectedPhase.getPhase_start();
            Date endDate = selectedPhase.getPhase_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            phaseStartTextView.setText(tempStart);
            phaseEndTextView.setText(tempEnd);
            phaseTitleTextView.setText(selectedPhase.getPhase_name());
            phaseStatusTextView.setText(selectedPhase.getPhase_status());
            } else {
            Log.d(PhaseDetail.LOG_TAG, "selected Phase is null");
            selectedPhase = new Phase();
        }
    }

    //Query the database and update current layout with appropriate data:

    private void updateList() {
        List<WorkUnit> allPhaseWorkUnits = db.workUnitDao().getPhaseWorkUnitList(selectedPhase.getPhase_id());
        String[] items = new String[allPhaseWorkUnits.size()];
        if(!allPhaseWorkUnits.isEmpty()) {
            for (int i = 0; i < allPhaseWorkUnits.size(); i++) {
                items[i] = allPhaseWorkUnits.get(i).getWorkUnit_title();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        workUnitList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}