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
import com.example.projectguru.data.WorkUnit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorkUnitNoteEdit extends AppCompatActivity {

    public static String LOG_TAG = "WorkUnitNoteEditActivityLog";
    MainDatabase db;
    int projectId;
    int phaseId;
    int workUnitId;
    Intent intent;
    Project selectedProject;
    Phase selectedPhase;
    WorkUnit selectedWorkUnit;
    EditText workUnitNoteEditText;
    FloatingActionButton workUnitNoteSaveButton;

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
        setContentView(R.layout.activity_work_unit_note_edit);
        setTitle("Add or Edit Note");
        workUnitNoteEditText = findViewById(R.id.workUnitNoteEditText);
        workUnitNoteSaveButton = findViewById(R.id.workUnitNoteSaveButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        workUnitId = intent.getIntExtra("workUnitId", -1);
        selectedPhase = db.phaseDao().getPhase(projectId, phaseId);
        selectedWorkUnit = db.workUnitDao().getWorkUnit(phaseId, workUnitId);

        //Query the database and update current layout with appropriate data:

        updateViews();

        workUnitNoteSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedWorkUnit.setWorkUnit_notes(String.valueOf(workUnitNoteEditText.getText()));
                db.workUnitDao().updateWorkUnit(selectedWorkUnit);
                Intent intent = new Intent(getApplicationContext(), WorkUnitDetail.class);
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
            Log.d(WorkUnitNoteEdit.LOG_TAG, "selected WorkUnit is not null");
            workUnitNoteEditText.setText(selectedWorkUnit.getWorkUnit_notes());
        } else {
            Log.d(WorkUnitNoteEdit.LOG_TAG, "selected WorkUnit is null");
            selectedWorkUnit = new WorkUnit();
        }
    }
}