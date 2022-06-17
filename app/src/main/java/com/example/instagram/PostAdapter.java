package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private final List<Post> posts;
    private final Context context;

    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> items) {
        posts.addAll(items);
        notifyDataSetChanged();
    }

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvUser;
        private final ImageView ivImage;
        private final TextView tvDescription;
        private final TextView tvTimeAgo;
        private final ImageView ivProfileImage;
        private final TextView tvUser2;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimeAgo = itemView.findViewById(R.id.tvTimeAgo);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvUser2 = itemView.findViewById(R.id.tvUser2);
            itemView.setOnClickListener(this);

        }
        public void bind(Post post) {
            tvUser.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());
            ParseFile image = post.getImage();
            tvTimeAgo.setText(post.calculateTimeAgo());
            tvUser2.setText(post.getUser().getUsername());
            ParseFile profileImage = post.getUser().getParseFile("ProfileImage");

            if (profileImage != null){
                Glide.with(context).load(profileImage.getUrl())
                        .circleCrop()
                        .into(ivProfileImage);
            }

            if (image != null){

                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Post post = posts.get(position);
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("posts", post);
                context.startActivity(intent);
            }
        }
    }
}
