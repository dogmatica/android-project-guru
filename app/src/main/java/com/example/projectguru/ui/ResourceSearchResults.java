package com.example.projectguru.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.DedicatedResource;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Resource;
import com.example.projectguru.data.SharedResource;
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

        listViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int resourceId = resourceList.get(i).getResource_id();
                Resource selectedResource = db.resourceDao().getResource(resourceId);
                String resourceType = selectedResource.getResource_type();
                if (resourceType.equals("Dedicated")) {
                    final AlertDialog dialog = new AlertDialog.Builder(ResourceSearchResults.this).setTitle("Confirm").setMessage(
                            "Assign " + selectedResource.getResource_name() + " to " + selectedWorkUnit.getWorkUnit_title() + "?")
                            .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            selectedResource.setAssigned(1);
                            selectedResource.setWorkUnit_id(workUnitId);
                            db.resourceDao().updateResource(selectedResource);
                            String tempEmail = DedicatedResource.assignEmail(selectedResource);
                            String tempMessage = DedicatedResource.assignMessage(selectedResource);
                            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                            sendIntent.setData(Uri.parse("mailto:"));
                            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {tempEmail});
                            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Work Unit Assignment");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, tempMessage);
                            startActivity(Intent.createChooser(sendIntent, "Send Email"));
                        }
                    });
                } else {
                    final AlertDialog dialog = new AlertDialog.Builder(ResourceSearchResults.this).setTitle("Confirm").setMessage(
                            "Request " + selectedResource.getResource_name() + " for " + selectedWorkUnit.getWorkUnit_title() + "?")
                            .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            selectedResource.setRequested(1);
                            db.resourceDao().updateResource(selectedResource);
                            String tempEmail = SharedResource.requestEmail(selectedResource);
                            String tempMessage = SharedResource.requestMessage(selectedResource);
                            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                            sendIntent.setData(Uri.parse("mailto:"));
                            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {tempEmail});
                            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Work Unit Request");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, tempMessage);
                            startActivity(Intent.createChooser(sendIntent, "Send Email"));
                        }
                    });
                 }

            }
        });
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