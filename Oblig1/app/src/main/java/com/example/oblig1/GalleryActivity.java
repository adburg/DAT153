package com.example.oblig1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GalleryActivity extends AppCompatActivity implements GalleryAdapter.OnItemClickListener {

    private static final String TAG = "GalleryActivity";
    protected RecyclerView recyclerView;
    protected GalleryAdapter adapter;
    protected ArrayList<PhotoEntry> galleryItems;
    protected ActivityResultLauncher<Intent> galleryLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        recyclerView = findViewById(R.id.recycler_view);
        galleryItems = ((MyApplication) getApplication()).getPhotoEntries();
        adapter = new GalleryAdapter(galleryItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        // Prompt the user to enter a name for the image
                        showNameInputDialog(selectedImageUri);
                    }
                }
        );

        Button sortButtonAZ = findViewById(R.id.button_sort_az);
        sortButtonAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortGalleryItemsAZ();
            }
        });


        Button sortButtonZA = findViewById(R.id.button_sort_za);
        sortButtonZA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortGalleryItemsZA();
            }
        });


        Button addButton = findViewById(R.id.button_add_entry);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        Button saveButton = findViewById(R.id.button_save_exit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this, MainActivity.class));
            }
        });
    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }

    private void showNameInputDialog(final Uri imageUri) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Image Name");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String imageName = input.getText().toString();

                // Add the new GalleryItem to the list and refresh the RecyclerView
                ((MyApplication) getApplication()).addPhotoEntry(new PhotoEntry(imageName, imageUri));
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }
    @Override
    public void onItemClick(int position) {
        // Handle item click here
        showDeleteConfirmationDialog(position);
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Photo");
        builder.setMessage("Are you sure you want to delete this photo?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Remove the item from the list
                galleryItems.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, galleryItems.size());
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void sortGalleryItemsAZ() {
        // Sort the list of gallery items alphabetically based on their names
        Collections.sort(galleryItems, new Comparator<PhotoEntry>() {
            @Override
            public int compare(PhotoEntry item1, PhotoEntry item2) {
                // Compare the names of the gallery items alphabetically
                return item1.getName().compareTo(item2.getName());
            }
        });

        // Update the RecyclerView with the sorted list
        adapter.notifyDataSetChanged();
    }
    private void sortGalleryItemsZA() {
        // Sort the list of gallery items alphabetically based on their names
        Collections.sort(galleryItems, new Comparator<PhotoEntry>() {
            @Override
            public int compare(PhotoEntry item1, PhotoEntry item2) {
                // Compare the names of the gallery items alphabetically
                return item2.getName().compareTo(item1.getName());
            }
        });

        // Update the RecyclerView with the sorted list
        adapter.notifyDataSetChanged();
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
