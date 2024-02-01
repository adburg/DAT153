package com.example.oblig1;

import android.app.Application;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyApplication extends Application {
    private ArrayList<PhotoEntry> galleryItems;

    @Override
    public void onCreate() {
        super.onCreate();
        galleryItems = new ArrayList<>();
        initializeData();
    }
    private void initializeData() {
        galleryItems.add(new PhotoEntry("Elephant", Uri.parse("android.resource://com.example.oblig1/" + R.drawable.elefant)));
        galleryItems.add(new PhotoEntry("Car", Uri.parse("android.resource://com.example.oblig1/" + R.drawable.bil)));
        galleryItems.add(new PhotoEntry("Bedroom", Uri.parse("android.resource://com.example.oblig1/" + R.drawable.bedroom)));
    }
    public ArrayList<PhotoEntry> getPhotoEntries() {
        return galleryItems;
    }

    public void addPhotoEntry(PhotoEntry entry) {
        galleryItems.add(entry);
    }
}
