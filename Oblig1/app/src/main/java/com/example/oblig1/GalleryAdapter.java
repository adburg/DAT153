package com.example.oblig1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    protected List<PhotoEntry> galleryItems;
    protected OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public GalleryAdapter(List<PhotoEntry> galleryItems, OnItemClickListener listener) {
        this.galleryItems = galleryItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       PhotoEntry item = galleryItems.get(position);
        holder.bind(item, position, listener);
        holder.nameTextView.setText(item.getName());
        Picasso.get().load(item.getPhotoUri()).into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return galleryItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;
        TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.imageView_photo);
            nameTextView = itemView.findViewById(R.id.textView_name);
        }
        public void bind(final PhotoEntry item, final int position, final OnItemClickListener listener) {
            nameTextView.setText(item.getName());
            photoImageView.setImageURI(item.getPhotoUri());

            // Set click listener to delete the item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}

