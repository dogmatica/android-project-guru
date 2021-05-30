package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Resource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ResourceManage extends AppCompatActivity {

    ListView resourceListViewResults;
    FloatingActionButton resourceAddButton;
    public static String LOG_TAG = "ResourceManageActivityLog";
    MainDatabase db;
    Intent intent;
    List<Resource> resourceList;

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
        setContentView(R.layout.activity_resource_manage);
        setTitle("Manage Resources");
        resourceListViewResults = findViewById(R.id.resourceListViewResults);
        resourceAddButton = findViewById(R.id.resourceAddButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        resourceList = (List<Resource>) intent.getSerializableExtra("resourceList");

        updateList();

        resourceListViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int resourceId = resourceList.get(i).getResource_id();
                Intent intent = new Intent(getApplicationContext(), ResourceDetail.class);
                intent.putExtra("resourceId", resourceId);
                startActivity(intent);
            }
        });

        resourceAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResourceEdit.class);
                int dbCount = db.resourceDao().getResourceList().size() + 1;
                Resource tempResource = new Resource();
                String tempResourceName = "New Resource " + dbCount;
                tempResource.setResource_name(tempResourceName);
                tempResource.setResource_email("email");
                tempResource.setResource_phone("555-555-5555");
                tempResource.setResource_type("Dedicated");
                db.resourceDao().insertResource(tempResource);
                tempResource = db.resourceDao().getResourceByName(tempResourceName);
                int resourceId = tempResource.getResource_id();
                intent.putExtra("resourceId", resourceId);
                startActivity(intent);
            }
        });
    }

    private void updateList() {
        String[] items = new String[resourceList.size()];
        for (int i = 0; i < resourceList.size(); i++) {
            items[i] = resourceList.get(i).getResource_name();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        resourceListViewResults.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}