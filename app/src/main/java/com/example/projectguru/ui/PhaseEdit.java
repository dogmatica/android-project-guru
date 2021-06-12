package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;
import com.example.projectguru.data.Project;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PhaseEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String LOG_TAG = "PhaseEditActivityLog";
    MainDatabase db;
    EditText phaseNamePlainText;
    EditText phaseStartDate;
    EditText phaseEndDate;
    Spinner spinner;
    FloatingActionButton phaseSaveButton;
    int projectId;
    int phaseId;
    SimpleDateFormat formatter;
    Intent intent;
    Project selectedProject;
    Phase selectedPhase;
    Date newStartDate;
    Date newEndDate;

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
        setContentView(R.layout.activity_phase_edit);
        setTitle("Add or Edit Phase");
        phaseNamePlainText = findViewById(R.id.phaseNamePlainText);
        phaseStartDate = findViewById(R.id.phaseStartDate);
        phaseEndDate = findViewById(R.id.phaseEndDate);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.statuses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        phaseSaveButton = findViewById(R.id.phaseSaveButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        selectedProject = db.projectDao().getProject(projectId);
        selectedPhase = db.phaseDao().getPhase(projectId, phaseId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        //Query the database and update current layout with appropriate data:

        updateViews();

        phaseSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempName = String.valueOf(phaseNamePlainText.getText());
                int length = tempName.length();
                if (length < 1) {
                    Toast.makeText(getApplicationContext(), "Name cannot be blank.", Toast.LENGTH_LONG).show();
                } else {
                    //Gathering field entries and inserting into phase table
                    try {
                        //First the phase is created and inserted
                        Phase newPhase = new Phase();
                        newStartDate = formatter.parse(String.valueOf(phaseStartDate.getText()));
                        newEndDate = formatter.parse(String.valueOf(phaseEndDate.getText()));
                        newPhase.setPhase_id(phaseId);
                        newPhase.setProject_id_fk(projectId);
                        newPhase.setPhase_name(String.valueOf(phaseNamePlainText.getText()));
                        newPhase.setPhase_start(newStartDate);
                        newPhase.setPhase_end(newEndDate);
                        newPhase.setPhase_status(String.valueOf(spinner.getSelectedItem()));
                        db.phaseDao().updatePhase(newPhase);
                        Intent intent = new Intent(getApplicationContext(), ProjectDetail.class);
                        intent.putExtra("projectId", projectId);
                        intent.putExtra("phaseId", phaseId);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedPhase != null) {
            Log.d(PhaseEdit.LOG_TAG, "selected Phase is not null");
            Date startDate = selectedPhase.getPhase_start();
            Date endDate = selectedPhase.getPhase_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            phaseStartDate.setText(tempStart);
            phaseEndDate.setText(tempEnd);
            phaseNamePlainText.setText(selectedPhase.getPhase_name());
        } else {
            Log.d(PhaseEdit.LOG_TAG, "selected Phase is null");
            selectedPhase = new Phase();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}