package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Resource;
import com.example.projectguru.data.WorkUnit;

import java.io.Serializable;
import java.util.List;

public class ResourceSearch extends AppCompatActivity {

    EditText editTextTextPersonName;
    Button buttonSearch;
    Button buttonDedicated;
    Button buttonShared;
    public static String LOG_TAG = "ResourceSearchActivityLog";
    MainDatabase db;
    Intent intent;
    int workUnitId;
    int phaseId;
    int projectId;
    WorkUnit selectedWorkUnit;

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
        setContentView(R.layout.activity_resource_search);
        setTitle("Resource Search");
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonDedicated = findViewById(R.id.buttonDedicated);
        buttonShared = findViewById(R.id.buttonShared);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        workUnitId = intent.getIntExtra("workUnitId", -1);
        phaseId = intent.getIntExtra("phaseId", -1);
        projectId = intent.getIntExtra("projectId", -1);
        selectedWorkUnit = db.workUnitDao().getWorkUnit(phaseId, workUnitId);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = String.valueOf(editTextTextPersonName.getText());
                List<Resource> resourceList = db.resourceDao().getAllResourcesNamed(searchText);
                if (resourceList != null && resourceList.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), ResourceSearchResults.class);
                    intent.putExtra("projectId", projectId);
                    intent.putExtra("phaseId", phaseId);
                    intent.putExtra("workUnitId", workUnitId);
                    intent.putExtra("resourceList", (Serializable) resourceList);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No matches were found.", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonDedicated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Resource> resourceList = db.resourceDao().getAllResourcesOfType("Dedicated");
                Intent intent = new Intent(getApplicationContext(), ResourceSearchResults.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                intent.putExtra("resourceList", (Serializable) resourceList);
                startActivity(intent);
            }
        });

        buttonShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Resource> resourceList = db.resourceDao().getAllResourcesOfType("Shared");
                Intent intent = new Intent(getApplicationContext(), ResourceSearchResults.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("phaseId", phaseId);
                intent.putExtra("workUnitId", workUnitId);
                intent.putExtra("resourceList", (Serializable) resourceList);
                startActivity(intent);
            }
        });
    }
}