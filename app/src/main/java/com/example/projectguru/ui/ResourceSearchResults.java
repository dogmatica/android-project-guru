package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Resource;
import com.example.projectguru.data.WorkUnit;

import java.util.List;

public class ResourceSearchResults extends AppCompatActivity {

    ListView listViewResults;
    public static String LOG_TAG = "ResourceSearchActivityLog";
    MainDatabase db;
    Intent intent;
    int workUnitId;
    int phaseId;
    int projectId;
    List<Resource> resourceList;
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
        setContentView(R.layout.activity_resource_search_results);
        setTitle("Search Results");
        listViewResults = findViewById(R.id.listViewResults);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        workUnitId = intent.getIntExtra("workUnitId", workUnitId);
        phaseId = intent.getIntExtra("phaseId", phaseId);
        projectId = intent.getIntExtra("projectId", projectId);
        resourceList = (List<Resource>) intent.getSerializableExtra("resourceList");
        selectedWorkUnit = db.workUnitDao().getWorkUnit(phaseId, workUnitId);

        updateList();
    }

    private void updateList() {
        String[] items = new String[resourceList.size()];
        for (int i = 0; i < resourceList.size(); i++) {
                items[i] = resourceList.get(i).getResource_name();
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listViewResults.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}