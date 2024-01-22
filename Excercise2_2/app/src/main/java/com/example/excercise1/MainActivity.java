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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button activityTwoButton = findViewById(R.id.activity2);

        activityTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startSubActivity();
            }
        });
    }
    private void startSubActivity() {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);

        ActivityResultLauncher<Intent> subActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        handleSubActivityResult(result.getData());
                    }
                });

        subActivityLauncher.launch(intent);
    }

    private void handleSubActivityResult(Intent data) {
        int randomNumber = data.getIntExtra("randomNumber", 0);
        TextView randomNumberText = findViewById(R.id.randomNumberText);
        randomNumberText.setText("Random Number: " + randomNumber);
        // Now you can use the resultString as needed (e.g., display it in a TextView)
        Log.d(TAG, "Result received in MainActivity: " + randomNumber);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
}