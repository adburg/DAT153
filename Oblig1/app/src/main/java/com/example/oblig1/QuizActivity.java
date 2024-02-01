package com.example.oblig1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "QuizActivity";
    private ImageView imageView;
    private Button[] optionButtons;
    private TextView scoreTextView;
    private List<PhotoEntry> galleryItems;
    private PhotoEntry currentPhoto;
    private String correctName;
    private int score = 0;
    private int totalAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        imageView = findViewById(R.id.imageView_quiz);
        optionButtons = new Button[3];
        optionButtons[0] = findViewById(R.id.button_option1);
        optionButtons[1] = findViewById(R.id.button_option2);
        optionButtons[2] = findViewById(R.id.button_option3);
        scoreTextView = findViewById(R.id.textView_score);

        // Get the list of gallery items from MyApplication
        galleryItems = ((MyApplication) getApplication()).getPhotoEntries();

        Button homeButton = findViewById(R.id.button_home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizActivity.this, MainActivity.class));
            }
        });

        // Set click listeners for option buttons
        for (Button button : optionButtons) {
            button.setOnClickListener(this);
        }

        // Start the quiz
        startQuiz();
    }
    private void startQuiz() {
        // Randomly select a photo from the gallery
        currentPhoto = getRandomPhoto();
        if (currentPhoto != null) {
            // Display the selected photo
            imageView.setImageURI(currentPhoto.getPhotoUri());

            // Generate options
            List<String> options = generateOptions(currentPhoto.getName());

            // Shuffle options
            Collections.shuffle(options);

            // Display options on buttons
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options.get(i));
            }

            // Set the correct name
            correctName = currentPhoto.getName();
        }
    }
    private PhotoEntry getRandomPhoto() {
        if (galleryItems != null && !galleryItems.isEmpty()) {
            Random random = new Random();
            return galleryItems.get(random.nextInt(galleryItems.size()));
        }
        return null;
    }
    private List<String> generateOptions(String correctOption) {
        List<String> options = new ArrayList<>();
        options.add(correctOption);

        // Add two incorrect options (randomly chosen from the gallery items)
        while (options.size() < 3) {
            PhotoEntry randomPhoto = getRandomPhoto();
            if (randomPhoto != null && !randomPhoto.getName().equals(correctOption) && !options.contains(randomPhoto.getName())) {
                options.add(randomPhoto.getName());
            }
        }
        return options;
    }
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        String selectedName = clickedButton.getText().toString();

        // Check if the selected option is correct
        if (selectedName.equals(correctName)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Incorrect. Correct answer: " + correctName, Toast.LENGTH_SHORT).show();
        }

        // Update score and total attempts
        totalAttempts++;
        updateScore();

        // Start a new quiz round
        startQuiz();
    }
    private void updateScore() {
        String scoreText = "Score: " + score + " / " + totalAttempts;
        scoreTextView.setText(scoreText);
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
