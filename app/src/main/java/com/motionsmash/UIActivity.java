package com.motionsmash;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.ms.R;
import com.google.android.material.button.MaterialButton;

public class UIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_dashboard);
        }

        TextView highScoreValue = findViewById(R.id.highScoreValue);
        TextView totalGamesValue = findViewById(R.id.totalGamesValue);
        MaterialButton btnBack = findViewById(R.id.btnBack);

        // Dummy data for now
        highScoreValue.setText("1250");
        totalGamesValue.setText("42");

        btnBack.setOnClickListener(v -> finish());
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
