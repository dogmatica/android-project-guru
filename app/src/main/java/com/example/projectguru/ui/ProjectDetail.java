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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Phase;
import com.example.projectguru.data.Project;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProjectDetail extends AppCompatActivity {

    public static String LOG_TAG = "ProjectDetailActivityLog";
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
    protected void onResume() {
        super.onResume();
        updateList();
        updateViews();
    }

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
        setContentView(R.layout.activity_project_detail);
        setTitle("Project Details");
        addProjectPhaseButton = findViewById(R.id.addProjectPhaseButton);
        editProjectButton = findViewById(R.id.editProjectButton);
        deleteProjectButton = findViewById(R.id.deleteProjectButton);
        projectTitleTextView = findViewById(R.id.projectTitleTextView);
        startDateTextView = findViewById(R.id.textViewEmail);
        endDateTextView = findViewById(R.id.textViewPhone);
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

        phaseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Loading the term detail view, passing variable termId:
                Intent intent = new Intent(getApplicationContext(), PhaseDetail.class);
                int phaseId;
                List<Phase> phasesList = db.phaseDao().getPhaseList(projectId);
                phaseId = phasesList.get(position).getPhase_id();
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                startActivity(intent);
            }
        });

        editProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProjectEdit.class);
                intent.putExtra("projectId", projectId);
                startActivity(intent);
            }
        });

        addProjectPhaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhaseEdit.class);
                Calendar calendar = Calendar.getInstance();
                int dbCount = db.phaseDao().getAllPhases().size() + 1;
                Phase tempPhase = new Phase();
                String tempPhaseName = "New Phase " + dbCount;
                tempPhase.setPhase_name(tempPhaseName);
                tempPhase.setPhase_start(calendar.getTime());
                tempPhase.setPhase_end(calendar.getTime());
                tempPhase.setPhase_status("Planned");
                tempPhase.setPhase_notes("Notes go here.");
                tempPhase.setProject_id_fk(projectId);
                db.phaseDao().insertPhase(tempPhase);
                tempPhase = db.phaseDao().getPhaseByName(projectId, tempPhaseName);
                int phaseId = tempPhase.getPhase_id();
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                startActivity(intent);
            }
        });

        deleteProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(ProjectDetail.this).setTitle("Confirm").setMessage("Delete Project?")
                        .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int numPhases = db.phaseDao().getPhaseList(projectId).size();
                        if (numPhases == 0) {
                            String projectName = selectedProject.getProject_name();
                            db.projectDao().deleteProject(projectId);
                            Toast.makeText(getApplicationContext(), "Project " + projectName + " was deleted.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), ProjectsList.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Cannot delete a project that has phases.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


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