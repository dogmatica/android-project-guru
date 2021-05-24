package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.WorkUnit;

public class WorkUnitNote extends AppCompatActivity {

    public static String LOG_TAG = "WorkUnitNoteActivityLog";
    MainDatabase db;
    int projectId;
    int phaseId;
    int workUnitId;
    WorkUnit selectedWorkUnit;
    Intent intent;
    TextView workUnitNote;
    Button editWorkUnitNotesButton;

    //Inflation of hidden menu on action bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        inflater.inflate(R.menu.menu_note_share, menu);
        return true;
    }

    //Actions related to hidden menu selection

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //The hidden menu in the WorkUnitNote view provides additional options:
        switch (item.getItemId()) {
            //When "Home" is selected:
            case R.id.home:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                return true;
            //When Share is selected:
            case R.id.share:
                //A chooser is displayed enabling the user to select their method of sharing
                //The note text is passed to the user's selection in plain text
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, workUnitNote.getText());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_unit_note);
        setTitle("Work Unit Note");
        workUnitNote = findViewById(R.id.workUnitNote);
        editWorkUnitNotesButton = findViewById(R.id.editWorkUnitNotesButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        workUnitId = intent.getIntExtra("workUnitId", -1);
        selectedWorkUnit = db.workUnitDao().getWorkUnit(phaseId, workUnitId);

        //Query the database and update current layout with appropriate data:

        updateViews();

        editWorkUnitNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loading the add / edit note view, passing variables courseId and termId:
                Intent intent = new Intent(getApplicationContext(), WorkUnitNoteEdit.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                startActivity(intent);
            }
        });
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