package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.Resource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResourceEdit extends AppCompatActivity {

    public static String LOG_TAG = "ResourceEditActivityLog";
    MainDatabase db;
    EditText resourceNamePlainText;
    EditText resourcePhone;
    EditText resourceEmail;
    Spinner spinner;
    FloatingActionButton resourceSaveButton;
    int resourceId;
    Intent intent;
    Resource selectedResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_edit);
        setTitle("Add or Edit Resource");
        resourceNamePlainText = findViewById(R.id.resourceNamePlainText);
        resourcePhone = findViewById(R.id.resourcePhone);
        resourceEmail = findViewById(R.id.resourceEmail);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        resourceSaveButton = findViewById(R.id.resourceSaveButton);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        resourceId = intent.getIntExtra("resourceId", -1);
        selectedResource = db.resourceDao().getResource(resourceId);

        //Query the database and update current layout with appropriate data:

        updateViews();
    }

    //Query the database and update current layout with appropriate data:

    private void updateViews() {
        if (selectedResource != null) {
            Log.d(WorkUnitEdit.LOG_TAG, "selected Resource is not null");
            resourceNamePlainText.setText(selectedResource.getResource_name());
            resourcePhone.setText(selectedResource.getResource_phone());
            resourceEmail.setText(selectedResource.getResource_email());
        } else {
            Log.d(WorkUnitEdit.LOG_TAG, "selected Resource is null");
            selectedResource = new Resource();
        }
    }
}