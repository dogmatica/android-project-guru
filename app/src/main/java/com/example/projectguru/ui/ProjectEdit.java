package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Project;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectEdit extends AppCompatActivity {

    public static String LOG_TAG = "ProjectEditActivityLog";
    MainDatabase db;
    EditText projectNamePlainText;
    EditText projectStartDate;
    EditText projectEndDate;
    Spinner spinner;
    FloatingActionButton projectSaveButton;
    int projectId;
    SimpleDateFormat formatter;
    Intent intent;
    Project selectedProject;
    Date newStartDate;
    Date newEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_edit);
        setTitle("Add or Edit Project");
        projectNamePlainText = findViewById(R.id.projectNamePlainText);
        projectStartDate = findViewById(R.id.projectStartDate);
        projectEndDate = findViewById(R.id.projectEndDate);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.statuses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        projectSaveButton = findViewById(R.id.projectSaveButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        selectedProject = db.projectDao().getProject(projectId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        //Query the database and update current layout with appropriate data:

        updateViews();
    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedProject != null) {
            Log.d(ProjectEdit.LOG_TAG, "selected Project is not null");
            Date startDate = selectedProject.getProject_start();
            Date endDate = selectedProject.getProject_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            projectStartDate.setText(tempStart);
            projectEndDate.setText(tempEnd);
            projectNamePlainText.setText(selectedProject.getProject_name());
        } else {
            Log.d(ProjectEdit.LOG_TAG, "selected Project is null");
            selectedProject = new Project();
        }
    }
}