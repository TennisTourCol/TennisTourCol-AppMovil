package com.tennistourcol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        TextView textView = findViewById(R.id.textViewNotification);

        String message =  getIntent().getStringExtra("message");

        textView.setText(message);
    }
}
