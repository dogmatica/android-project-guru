package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;

public class PhaseNote extends AppCompatActivity {

    public static String LOG_TAG = "PhaseNoteActivityLog";
    MainDatabase db;
    int projectId;
    int phaseId;
    Phase selectedPhase;
    Intent intent;
    TextView phaseNote;
    Button editPhaseNotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_note);
        setTitle("Phase Note");
        phaseNote = findViewById(R.id.phaseNote);
        editPhaseNotesButton = findViewById(R.id.editPhaseNotesButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        selectedPhase = db.phaseDao().getPhase(projectId, phaseId);

        //Query the database and update current layout with appropriate data:

        updateViews();
    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedPhase != null) {
            Log.d(PhaseNote.LOG_TAG, "selected Phase is not null");
            phaseNote.setText(selectedPhase.getPhase_notes());
        } else {
            Log.d(PhaseNote.LOG_TAG, "selected Phase is null");
            selectedPhase = new Phase();
        }
    }
}