package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;
import com.example.projectguru.data.Project;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectDetail extends AppCompatActivity {

    public static String LOG_TAG = "TermDetailActivityLog";
    MainDatabase db;
    FloatingActionButton addProjectPhaseButton;
    FloatingActionButton editProjectButton;
    FloatingActionButton deleteProjectButton;
    ListView phaseList;
    TextView projectTitleTextView;
    TextView startDateTextView;
    TextView endDateTextView;
    TextView projectStartTextView;
    TextView projectEndTextView;
    Intent intent;
    int projectId;
    Project selectedProject;
    SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        setTitle("Term Details");
        addProjectPhaseButton = findViewById(R.id.addProjectPhaseButton);
        editProjectButton = findViewById(R.id.editProjectButton);
        deleteProjectButton = findViewById(R.id.deleteProjectButton);
        projectTitleTextView = findViewById(R.id.projectTitleTextView);
        startDateTextView = findViewById(R.id.startDateTextView);
        endDateTextView = findViewById(R.id.endDateTextView);
        projectStartTextView = findViewById(R.id.projectStartTextView);
        projectEndTextView = findViewById(R.id.projectEndTextView);
        phaseList = findViewById(R.id.phaseList);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        projectId = intent.getIntExtra("projectId", -1);
        selectedProject = db.projectDao().getProject(projectId);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        //Query the database and update current layout with appropriate data:

        updateViews();
        updateList();
    }

    private void updateList() {
        List<Phase> allProjectPhases = db.phaseDao().getPhaseList(projectId);
        String[] items = new String[allProjectPhases.size()];
        if(!allProjectPhases.isEmpty()) {
            for (int i = 0; i < allProjectPhases.size(); i++) {
                items[i] = allProjectPhases.get(i).getPhase_name();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        phaseList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedProject != null) {
            Log.d(ProjectDetail.LOG_TAG, "selected Project is not null");
            Date startDate = selectedProject.getProject_start();
            Date endDate = selectedProject.getProject_end();
            String tempStart = formatter.format(startDate);
            String tempEnd = formatter.format(endDate);
            projectStartTextView.setText(tempStart);
            projectEndTextView.setText(tempEnd);
            projectTitleTextView.setText(selectedProject.getProject_name());
        } else {
            Log.d(ProjectDetail.LOG_TAG, "selected Project is null");
            selectedProject = new Project();
        }
    }
}