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
        //The hidden menu in the PhaseNote view provides additional options:
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, phaseNote.getText());
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

        editPhaseNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Loading the add / edit note view, passing variables courseId and termId:
                Intent intent = new Intent(getApplicationContext(), PhaseNoteEdit.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                startActivity(intent);
            }
        });
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