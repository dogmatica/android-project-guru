package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;
import com.example.projectguru.data.Project;
import com.example.projectguru.data.WorkUnit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PhaseDetail extends AppCompatActivity {

    public static String LOG_TAG = "PhaseDetailActivityLog";
    MainDatabase db;
    TextView phaseTitleTextView;
    TextView phaseStartTextView;
    TextView phaseEndTextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_detail);
        setTitle("Phase Details");
        phaseTitleTextView = findViewById(R.id.phaseTitleTextView);
        phaseStartTextView = findViewById(R.id.phaseStartTextView);
        phaseEndTextView = findViewById(R.id.phaseEndTextView);
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