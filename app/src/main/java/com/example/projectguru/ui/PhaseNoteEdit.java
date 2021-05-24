package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;
import com.example.projectguru.data.Project;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PhaseNoteEdit extends AppCompatActivity {

    public static String LOG_TAG = "PhaseNoteEditActivityLog";
    MainDatabase db;
    int projectId;
    int phaseId;
    Intent intent;
    Project selectedProject;
    Phase selectedPhase;
    EditText phaseNoteEditText;
    FloatingActionButton phaseNoteSaveButton;

    //Inflation of hidden menu on action bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    //Actions related to hidden menu selection

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //When "Home" is selected:
            case R.id.home:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_note_edit);
        setTitle("Add or Edit Note");
        phaseNoteEditText = findViewById(R.id.phaseNoteEditText);
        phaseNoteSaveButton = findViewById(R.id.phaseNoteSaveButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        selectedProject = db.projectDao().getProject(projectId);
        selectedPhase = db.phaseDao().getPhase(projectId, phaseId);

        //Query the database and update current layout with appropriate data:

        updateViews();

        phaseNoteSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPhase.setPhase_notes(String.valueOf(phaseNoteEditText.getText()));
                db.phaseDao().updatePhase(selectedPhase);
                Intent intent = new Intent(getApplicationContext(), PhaseDetail.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                startActivity(intent);
            }
        });
    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedPhase != null) {
            Log.d(PhaseNoteEdit.LOG_TAG, "selected Phase is not null");
            phaseNoteEditText.setText(selectedPhase.getPhase_notes());
        } else {
            Log.d(PhaseNoteEdit.LOG_TAG, "selected Phase is null");
            selectedPhase = new Phase();
        }
    }
}