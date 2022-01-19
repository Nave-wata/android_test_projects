package com.example.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        EditText edit = findViewById(R.id.editTextTextPersonName);
        String getText = edit.getText().toString();

        Intent intent = new Intent(getApplication(), DisplayMessageActivity.class);
        intent.putExtra("sendText", getText);
        startActivity(intent);
    }
}