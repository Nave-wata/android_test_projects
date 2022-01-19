package com.example.notepad;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the String
        String message = getIntent().getStringExtra(EXTRA_MESSAGE);
        Intent intent = getIntent();
        String data1 = getIntent().getStringExtra(MainActivity.data1);

        // Capture the layout's TextView and set the String as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText();
    }
}