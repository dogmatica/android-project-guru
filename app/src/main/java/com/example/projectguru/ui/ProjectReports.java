package com.example.projectguru.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.tools.ProjectAdapter;

public class ProjectReports extends AppCompatActivity {

    RecyclerView projectRecyclerView;
    ProjectAdapter adapter;
    MainDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_reports);

        projectRecyclerView = findViewById(R.id.projectRecyclerView);
        setRecyclerView();
    }

    private void setRecyclerView() {
        projectRecyclerView.setHasFixedSize(true);
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(this, db.projectDao().getProjectList());
        projectRecyclerView.setAdapter(adapter);
    }


}