package com.example.projectguru.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.NukeDatabase;
import com.example.projectguru.data.PopulateDatabase;
import com.example.projectguru.data.Project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Home extends AppCompatActivity {

    public static String LOG_TAG = "Home";
    TextView todaysDateTextView;
    TextView projectsPendingCountTextView;
    TextView completedCountTextView;
    TextView planCountTextView;
    Button goButton;
    Button projectsButton;
    Button phasesButton;
    Button workUnitsButton;
    MainDatabase db;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate todaysDate = LocalDate.now();
    String todaysDateString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
        db = MainDatabase.getInstance(getApplicationContext());
        todaysDateTextView = findViewById(R.id.todaysDateTextView);
        projectsPendingCountTextView = findViewById(R.id.projectsPendingCountTextView);
        completedCountTextView = findViewById(R.id.completedCountTextView);
        planCountTextView = findViewById(R.id.planCountTextView);
        goButton = findViewById(R.id.goButton);
        projectsButton = findViewById(R.id.projectsButton);
        phasesButton = findViewById(R.id.phasesButton);
        workUnitsButton = findViewById(R.id.workUnitsButton);
        todaysDateString = dtf.format(todaysDate);
        todaysDateTextView.setText(todaysDateString);

        //Query the database and update current layout with appropriate data:

        updateViews();

        //Layout and set established for programmatic display of ui elements

        ConstraintLayout myLayout = findViewById(R.id.homePageConstraintLayout);
        ConstraintSet set = new ConstraintSet();

        //Programmatic display of Populate Database button

        Button populateDBButton = new Button(getApplicationContext());
        populateDBButton.setText("Populate Empty Database");
        populateDBButton.setId(R.id.populateDBButton);
        set.constrainHeight(populateDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(populateDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(populateDBButton.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 8);
        set.connect(populateDBButton.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 8);
        myLayout.addView(populateDBButton);
        setContentView(myLayout);
        set.applyTo(myLayout);

        //Programmatic display of Delete Database button

        Button nukeDBButton = new Button(getApplicationContext());
        nukeDBButton.setText("Delete Database");
        nukeDBButton.setId(R.id.nukeDBButton);
        set.constrainHeight(nukeDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(nukeDBButton.getId(), ConstraintSet.WRAP_CONTENT);
        set.connect(nukeDBButton.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 8);
        set.connect(nukeDBButton.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 8);
        myLayout.addView(nukeDBButton);
        setContentView(myLayout);
        set.applyTo(myLayout);

        //When the Populate Database button is pressed:

        populateDBButton.setOnClickListener(v -> {
            Log.d(LOG_TAG, "populate DB button pressed");
            PopulateDatabase populateDatabase = new PopulateDatabase();
            populateDatabase.populate(getApplicationContext());
            updateViews();
        });

        //When the Delete Database button is pressed:

        nukeDBButton.setOnClickListener(v -> {
            Log.d(LOG_TAG, "nuke DB button pressed");
            NukeDatabase nukeDatabase = new NukeDatabase();
            nukeDatabase.nuke(getApplicationContext());
            updateViews();
        });

    }

    private void updateViews() {
        //A collection of data is queried from the database to populate progress tracking elements
        int projectsPending = 0;
        int projectsCompleted = 0;
        int projectsPlanned = 0;
        try {
            List<Project> projectList = db.projectDao().getAllProjects();
            try {
                for (int i = 0; i < projectList.size(); i++) {
                    if (projectList.get(i).getProject_status().contains("In Progress"))
                        projectsPending++;
                    if (projectList.get(i).getProject_status().contains("Completed"))
                        projectsCompleted++;
                    if (projectList.get(i).getProject_status().contains("Planned"))
                        projectsPlanned++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        projectsPendingCountTextView.setText(String.valueOf(projectsPending));
        completedCountTextView.setText(String.valueOf(projectsCompleted));
        planCountTextView.setText(String.valueOf(projectsPlanned));
    }
}