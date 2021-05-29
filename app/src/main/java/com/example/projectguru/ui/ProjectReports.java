package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Project;
import com.example.projectguru.tools.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProjectReports extends AppCompatActivity {

    RecyclerView rvProjects;
    ProjectAdapter adapter;
    MainDatabase db;
    List<Project> projectList = new ArrayList<>();

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
        setContentView(R.layout.activity_project_reports);
        db = MainDatabase.getInstance(getApplicationContext());
        projectList = db.projectDao().getProjectList();
        rvProjects = findViewById(R.id.rvProjects);
        rvProjects.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvProjects.setLayoutManager(layoutManager);
        ProjectAdapter projectAdapter = new ProjectAdapter(this, projectList, rvProjects);
        rvProjects.setAdapter(projectAdapter);
    }
/*
    private void setRecyclerView() {
        rvProjects.setHasFixedSize(true);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(this, db.projectDao().getAllProjects());
        rvProjects.setAdapter(adapter);
    }*/


}