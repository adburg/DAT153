package com.example.excercise1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "SubActivity";
    private int counter = 0;
    private ActivityResultLauncher<String> setResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button buttonA = findViewById(R.id.buttonA);
        Button buttonB = findViewById(R.id.buttonB);
        Button buttonC = findViewById(R.id.buttonC);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubActivity.this, MainActivity.class));
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubActivity.this, MainActivity.class));
                finish();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        int counterFromMain = getIntent().getIntExtra("counterValue", 0);
        Log.d(TAG, "Counter value received from main to sub:" + counterFromMain);
        TextView counterTextMainToSub = findViewById(R.id.counterFromMain);
        counterTextMainToSub.setText("Counter from main to sub: " + counterFromMain);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        //increment counter
        counter++;
        updateCounterOnScreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void updateCounterOnScreen() {
        // Update the TextView on the screen with the counter value
        TextView counterTextView = findViewById(R.id.counterTextView);
        counterTextView.setText("Counter: " + counter);
    }
}