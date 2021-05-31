package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainDatabase db;
    Button beginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Project Guru");
        db = MainDatabase.getInstance(getApplicationContext());
        beginButton = findViewById(R.id.beginButton);

        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userList = db.userDao().getAllUsers();
                if (userList != null && userList.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), PinEntry.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), PinSet.class);
                    startActivity(intent);
                }
            }
        });
    }
}