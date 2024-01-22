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

import java.util.Random;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "SubActivity";
    private ActivityResultLauncher<Intent> setResultLauncher;


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


        setResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        handleResult(result.getData());
                    }
                });
        int randomNumber = generateRandomNumber();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("randomNumber", randomNumber);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    private void handleResult(Intent data) {
        String resultString = data.getStringExtra("result");
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", resultString);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private int generateRandomNumber() {
        // Generate a random number (for example, between 1 and 100)
        Random random = new Random();
        return random.nextInt(100) + 1;
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
}