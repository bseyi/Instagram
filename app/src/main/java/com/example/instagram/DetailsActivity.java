package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcel;
import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {
    private Post post;

    private TextView tvUser;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvCreatedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        post = getIntent().getParcelableExtra("posts");
        tvUser = findViewById(R.id.tvUser);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);

        tvUser.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        tvCreatedAt.setText(post.calculateTimeAgo());

        ParseFile image = post.getImage();

        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }

    }
}