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
import com.example.projectguru.data.Phase;
import com.example.projectguru.tools.PhaseAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PhaseReports extends AppCompatActivity {

    RecyclerView rvPhases;
    MainDatabase db;
    List<Phase> phaseList = new ArrayList<>();
    SimpleDateFormat formatter;

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
        setContentView(R.layout.activity_phase_reports);
        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Calendar titleCalendar = Calendar.getInstance();
        Date timeStamp = titleCalendar.getTime();
        String formattedStamp = formatter.format(timeStamp);
        setTitle("Phase Report " + formattedStamp);
        db = MainDatabase.getInstance(getApplicationContext());
        phaseList = db.phaseDao().getAllPhases();
        rvPhases = findViewById(R.id.rvPhases);
        rvPhases.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPhases.setLayoutManager(layoutManager);
        PhaseAdapter phaseAdapter = new PhaseAdapter(this, phaseList, rvPhases);
        rvPhases.setAdapter(phaseAdapter);
    }
}