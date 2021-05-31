package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Resource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

public class ResourceDetail extends AppCompatActivity {

    TextView resourceNameTextView;
    TextView textViewResourceEmail;
    TextView textViewResourcePhone;
    TextView textViewResourceType;
    FloatingActionButton editResourceButton;
    FloatingActionButton deleteResourceButton;
    MainDatabase db;
    Intent intent;
    int resourceId;
    Resource selectedResource;
    public static String LOG_TAG = "ResourceDetailActivityLog";

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
        setContentView(R.layout.activity_resource_detail);
        setTitle("Resource Detail");
        resourceNameTextView = findViewById(R.id.phaseTitleTextView);
        textViewResourceEmail = findViewById(R.id.phaseStartTextView);
        textViewResourcePhone = findViewById(R.id.phaseEndTextView);
        textViewResourceType = findViewById(R.id.phaseStatusTextView);
        editResourceButton = findViewById(R.id.editResourceButton);
        deleteResourceButton = findViewById(R.id.deleteResourceButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        resourceId = intent.getIntExtra("resourceId", -1);
        selectedResource = db.resourceDao().getResource(resourceId);

        updateViews();

        editResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResourceEdit.class);
                intent.putExtra("resourceId", resourceId);
                startActivity(intent);
            }
        });

        deleteResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(ResourceDetail.this).setTitle("Confirm").setMessage("Delete Resource?")
                        .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String resourceName = selectedResource.getResource_name();
                        db.resourceDao().deleteResource(resourceId);
                        Toast.makeText(getApplicationContext(), "Resource " + resourceName + " was deleted.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ResourceManage.class);
                        List<Resource> resourceList = db.resourceDao().getResourceList();
                        intent.putExtra("resourceList", (Serializable) resourceList);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    private void updateViews() {
        if (selectedResource != null) {
            Log.d(WorkUnitEdit.LOG_TAG, "selected Resource is not null");
            resourceNameTextView.setText(selectedResource.getResource_name());
            textViewResourcePhone.setText(selectedResource.getResource_phone());
            textViewResourceEmail.setText(selectedResource.getResource_email());
            textViewResourceType.setText(selectedResource.getResource_type());
        } else {
            Log.d(ResourceDetail.LOG_TAG, "selected Resource is null");
            selectedResource = new Resource();
        }
    }
}