package com.example.retrievision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ReportLostObjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_object);

        Button button1 = findViewById(R.id.startButton);

        button1.setOnClickListener(view->openUploadImageLost());
    }
    public void openUploadImageLost()
    {
        Intent intent = new Intent(this, LostImageActivity.class);
        startActivity(intent);
    }
}