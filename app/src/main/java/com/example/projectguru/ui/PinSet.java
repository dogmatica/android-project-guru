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

import java.security.SecureRandom;

public class PinSet extends AppCompatActivity {

    EditText editTextPin;
    Button buttonSavePin;
    public static String LOG_TAG = "PhaseEditActivityLog";
    MainDatabase db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_set);
        editTextPin = findViewById(R.id.editTextPin);
        buttonSavePin = findViewById(R.id.buttonSavePin);
        db = MainDatabase.getInstance(getApplicationContext());
        intent = getIntent();


        buttonSavePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempPin = String.valueOf(editTextPin.getText());
                int length = tempPin.length();
                if (length == 4) {
                    SecureRandom random = new SecureRandom();
                    byte[] salt = new byte[16];
                    random.nextBytes(salt);
                    String hashedPin = PinHasher.hash(tempPin, salt);
                    User user = new User();
                    user.setSalt(salt);
                    user.setUser_pin(hashedPin);
                    db.userDao().insertUser(user);
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "PIN must be exactly 4 digits.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}