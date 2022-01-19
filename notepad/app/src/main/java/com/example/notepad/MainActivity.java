package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "com.example.notepad.DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = findViewById(R.id.button);
        String EXTRA_DATA = "com.example.notepad.DATA";
        String data1 = "Hello World!";

        sendButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
            intent.putExtra("EXTRA_DATA", data1);
            startActivity(intent);
        });
    }
}