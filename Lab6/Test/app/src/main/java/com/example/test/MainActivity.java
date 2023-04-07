package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
    }

    // called when user clicks "Click Me!" button
    public void onUpdateCounterButtonClick(View v) {
        // ensures counter does not progress past 10
        if (count < 10) {
            count++; // update counter
            // use the TextView's ID to access that object
            TextView counterView = findViewById(R.id.counterField);
            // update its text
            counterView.setText(String.valueOf(count));
        }
    }

}