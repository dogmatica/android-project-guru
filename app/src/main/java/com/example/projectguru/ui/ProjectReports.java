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
import com.example.projectguru.tools.ProjectAdapter;

public class ProjectReports extends AppCompatActivity {

    RecyclerView projectRecyclerView;
    ProjectAdapter adapter;
    MainDatabase db;

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