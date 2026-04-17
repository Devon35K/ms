package com.motionsmash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ms.R;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private Button startButton;
    private Button calibrateButton;
    private Button dashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        statusText = findViewById(R.id.statusText);
        startButton = findViewById(R.id.startButton);
        calibrateButton = findViewById(R.id.calibrateButton);
        dashboardButton = findViewById(R.id.dashboardButton);

        // Start Game function
        startButton.setOnClickListener(v -> {
            startGame();
        });

        // Calibrate function
        calibrateButton.setOnClickListener(v -> {
            startCalibration();
        });

        // Dashboard function
        dashboardButton.setOnClickListener(v -> {
            openDashboard();
        });
    }

    private void startGame() {
        statusText.setText(R.string.status_started);
        Toast.makeText(this, "Starting Game...", Toast.LENGTH_SHORT).show();
        // Add logic to transition to game screen
    }

    private void startCalibration() {
        statusText.setText(R.string.status_calibrating);
        Toast.makeText(this, "Opening Calibration...", Toast.LENGTH_SHORT).show();
        // Add logic to transition to calibration screen
    }

    private void openDashboard() {
        Intent intent = new Intent(this, UIActivity.class);
        startActivity(intent);
    }
}
