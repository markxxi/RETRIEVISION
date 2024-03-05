package com.example.retrievision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ReportFoundObjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_found_object);

        Button start = findViewById(R.id.startButton);
        start.setOnClickListener(view->openFoundImageActivity());
    }
    public void openFoundImageActivity(){
        Intent intent = new Intent(this, FoundImageActivity.class);
        startActivity(intent);
    }
}