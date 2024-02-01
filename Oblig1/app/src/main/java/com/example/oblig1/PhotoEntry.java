package com.example.oblig1;

import android.net.Uri;
import android.os.Parcel;

import java.io.Serializable;

public class PhotoEntry implements Serializable {
    protected Uri photoUri; // or int resourceId if using resource IDs
    protected String name;

    public PhotoEntry(String name, Uri photoUri) {
        this.photoUri = photoUri;
        this.name = name;
    }
    public Uri getPhotoUri() {
        return photoUri;
    }

    public String getName() {
        return name;
    }

}