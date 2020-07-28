package com.example.androidarchitecture;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button archActivityButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        archActivityButton = findViewById(R.id.arch_activity);
        archActivityButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ArchActivity.class);
            startActivity(intent);
        });
    }
}