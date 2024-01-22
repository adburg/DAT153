package com.example.excercise1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button activityTwoButton = findViewById(R.id.activity2);
        this.counter = 0;
        activityTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("counterValue", counter);
                startActivity(intent);
                counter++;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        //increment counter
        updateCounterOnScreen();
    }
    private void updateCounterOnScreen() {
        // Update the TextView on the screen with the counter value
        TextView counterTextView = findViewById(R.id.counterTextViewMain);
        counterTextView.setText("Counter: " + counter);
    }
}