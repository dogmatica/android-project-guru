package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;
import com.example.projectguru.data.Resource;
import com.example.projectguru.data.WorkUnit;
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