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
import com.example.projectguru.data.WorkUnit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkUnitEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String LOG_TAG = "WorkUnitEditActivityLog";
    MainDatabase db;
    EditText workUnitNamePlainText;
    EditText workUnitStartDate;
    EditText workUnitEndDate;
    Spinner spinner;
    FloatingActionButton workUnitSaveButton;
    int projectId;
    int phaseId;
    int workUnitId;
    SimpleDateFormat formatter;
    Intent intent;
    Project selectedProject;
    Phase selectedPhase;
    WorkUnit selectedWorkUnit;
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
        setContentView(R.layout.activity_work_unit_edit);
        setTitle("Add or Edit Work Unit");
        workUnitNamePlainText = findViewById(R.id.workUnitNamePlainText);
        workUnitStartDate = findViewById(R.id.workUnitStartDate);
        workUnitEndDate = findViewById(R.id.workUnitEndDate);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.statuses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        workUnitSaveButton = findViewById(R.id.workUnitSaveButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        workUnitId = intent.getIntExtra("workUnitId", -1);
        selectedProject = db.projectDao().getProject(projectId);
        selectedPhase = db.phaseDao().getPhase(projectId, phaseId);
        selectedWorkUnit = db.workUnitDao().getWorkUnit(phaseId, workUnitId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        //Query the database and update current layout with appropriate data:

        updateViews();

        workUnitSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempName = String.valueOf(workUnitNamePlainText.getText());
                int length = tempName.length();
                if (length < 1) {
                    Toast.makeText(getApplicationContext(), "Name cannot be blank.", Toast.LENGTH_LONG).show();
                } else {
                    //Gathering field entries and inserting into workunit table
                    try {
                        //First the workunit is created and inserted
                        WorkUnit newWorkUnit = new WorkUnit();
                        newStartDate = formatter.parse(String.valueOf(workUnitStartDate.getText()));
                        newEndDate = formatter.parse(String.valueOf(workUnitEndDate.getText()));
                        newWorkUnit.setWorkUnit_id(workUnitId);
                        newWorkUnit.setPhase_id_fk(phaseId);
                        newWorkUnit.setWorkUnit_title(String.valueOf(workUnitNamePlainText.getText()));
                        newWorkUnit.setWorkUnit_start(newStartDate);
                        newWorkUnit.setWorkUnit_end(newEndDate);
                        newWorkUnit.setWorkUnit_status(String.valueOf(spinner.getSelectedItem()));
                        db.workUnitDao().updateWorkUnit(newWorkUnit);
                        Intent intent = new Intent(getApplicationContext(), PhaseDetail.class);
                        intent.putExtra("projectId", projectId);
                        intent.putExtra("phaseId", phaseId);
                        intent.putExtra("workUnitId", workUnitId);
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
        if (selectedWorkUnit != null) {
            Log.d(WorkUnitEdit.LOG_TAG, "selected WorkUnit is not null");
            Date startDate = selectedWorkUnit.getWorkUnit_start();
            Date endDate = selectedWorkUnit.getWorkUnit_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            workUnitStartDate.setText(tempStart);
            workUnitEndDate.setText(tempEnd);
            workUnitNamePlainText.setText(selectedWorkUnit.getWorkUnit_title());
        } else {
            Log.d(WorkUnitEdit.LOG_TAG, "selected WorkUnit is null");
            selectedWorkUnit = new WorkUnit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}