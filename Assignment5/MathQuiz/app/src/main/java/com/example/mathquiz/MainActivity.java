package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    public static final int COUNTER_ACTIVITY_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.difficulty_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onLaunchButtonClick(View v) {
        Intent i = new Intent(this, QuizActivity.class);
        Spinner spinner = (Spinner) findViewById(R.id.difficulty_spinner);
        String difficulty = spinner.getSelectedItem().toString();

        // passes the difficulty to QuizActivity
        i.putExtra("Difficulty", difficulty);
        startActivityForResult(i, COUNTER_ACTIVITY_ID);
    }

}