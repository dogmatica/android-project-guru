package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Project;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class ProjectsList extends AppCompatActivity {

    public static String LOG_TAG = "ProjectsListActivityLog";
    MainDatabase db;
    ListView listView;
    FloatingActionButton addProjectButton;
    int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        setTitle("Project List");
        listView = findViewById(R.id.projectsListView);
        addProjectButton = findViewById(R.id.addProjectButton);
        db = MainDatabase.getInstance(getApplicationContext());

        //Query the database and update current layout with appropriate data:

        updateList();

        //When a term that is a member of the displayed list is pressed:

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Loading the term detail view, passing variable termId:
                Intent intent = new Intent(getApplicationContext(), ProjectDetail.class);
                int project_id;
                List<Project> projectsList = db.projectDao().getProjectList();
                project_id = projectsList.get(position).getProject_id();
                intent.putExtra("termId", project_id);
                startActivity(intent);
            }
        });

        //When the add term button under the terms list is pressed:

        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //A new blank term is created, populated with default values,
                //and loaded into the add / edit term view
                //variable termId is passed
                Intent intent = new Intent(getApplicationContext(), ProjectEdit.class);
                Calendar calendar = Calendar.getInstance();
                int dbCount = db.projectDao().getProjectList().size() + 1;
                Project tempProject = new Project();
                String tempTermName = "New Term " + dbCount;
                tempProject.setProject_name(tempTermName);
                tempProject.setProject_status("Planned");
                tempProject.setProject_start(calendar.getTime());
                tempProject.setProject_end(calendar.getTime());
                db.projectDao().insertProject(tempProject);
                tempProject = db.projectDao().getProjectByName(tempTermName);
                projectId = tempProject.getProject_id();
                intent.putExtra("termId", projectId);
                startActivity(intent);
            }
        });
    }

    //Query the database and update current layout with appropriate data:

    private void updateList() {
        List<Project> allProjects = db.projectDao().getProjectList();
        String[] items = new String[allProjects.size()];
        if(!allProjects.isEmpty()) {
            for (int i = 0; i < allProjects.size(); i++) {
                items[i] = allProjects.get(i).getProject_name();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}