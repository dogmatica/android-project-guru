package com.example.projectguru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectguru.R;
import com.example.projectguru.data.MainDatabase;
import com.example.projectguru.data.User;
import com.example.projectguru.tools.PinHasher;

public class PinEntry extends AppCompatActivity {

    EditText editTextPinEnter;
    Button buttonSubmitPin;
    MainDatabase db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_entry);
        editTextPinEnter = findViewById(R.id.editTextPinEnter);
        buttonSubmitPin = findViewById(R.id.buttonSubmitPin);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();

        buttonSubmitPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredPin = String.valueOf(editTextPinEnter.getText());
                User user = db.userDao().getUser();
                byte[] salt = user.getSalt();
                String enteredHash = PinHasher.hash(enteredPin, salt);
                String userHash = user.getUser_pin();
                if (enteredHash.equals(userHash)) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect PIN.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}