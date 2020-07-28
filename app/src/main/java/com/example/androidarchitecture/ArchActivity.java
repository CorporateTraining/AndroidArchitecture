package com.example.androidarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ArchActivity extends AppCompatActivity {
    private TextView archNumberTextView;
    private Button archStartButton;
    private ArchViewModel archViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arch);
        archNumberTextView = findViewById(R.id.arch_number);
        archStartButton = findViewById(R.id.arch_start);
        archViewModel = new ViewModelProvider(this).get(ArchViewModel.class);
        Observer<Integer> integerObserver = number -> {
            archNumberTextView.setText(String.valueOf(number));
        };
        archViewModel.getMutableLiveData().observe(this, integerObserver);
        archStartButton.setOnClickListener(view -> {
            archViewModel.increase();
        });
    }
}