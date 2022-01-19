package com.example.notepad;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        EditText edit = (EditText)findViewById(R.id.editTextTextPersonName);
        Editable getText = edit.getText();
        String testText = "I'm testText";

        Intent intent = new Intent(getApplication(), DisplayMessageActivity.class);
        intent.putExtra("sendText", testText);
        startActivity(intent);
    }
}