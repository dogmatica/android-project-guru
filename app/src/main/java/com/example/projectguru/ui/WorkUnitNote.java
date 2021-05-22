package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.WorkUnit;

public class WorkUnitNote extends AppCompatActivity {

    public static String LOG_TAG = "WorkUnitNoteActivityLog";
    MainDatabase db;
    int phaseId;
    int workUnitId;
    WorkUnit selectedWorkUnit;
    Intent intent;
    TextView workUnitNote;
    Button editWorkUnitNotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_unit_note);
        setTitle("Work Unit Note");
        workUnitNote = findViewById(R.id.workUnitNote);
        editWorkUnitNotesButton = findViewById(R.id.editWorkUnitNotesButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        phaseId = intent.getIntExtra("phaseId", -1);
        workUnitId = intent.getIntExtra("workUnitId", -1);
        selectedWorkUnit = db.workUnitDao().getWorkUnit(phaseId, workUnitId);

        //Query the database and update current layout with appropriate data:

        updateViews();
    }

    private void updateViews() {
        if (selectedWorkUnit != null) {
            Log.d(WorkUnitNote.LOG_TAG, "selected WorkUnit is not null");
            workUnitNote.setText(selectedWorkUnit.getWorkUnit_notes());
        } else {
            Log.d(WorkUnitNote.LOG_TAG, "selected WorkUnit is null");
            selectedWorkUnit = new WorkUnit();
        }
    }
}