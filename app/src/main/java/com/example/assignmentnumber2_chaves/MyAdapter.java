package com.example.assignmentnumber2_chaves;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MyAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.movie_layout, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText("Release Year: " + movie.getYear());
        holder.rating.setText("IMDb Rating: " + movie.getRating());
        Glide.with(context)
                .load(movie.getPosterUrl())
                .into(holder.imageView);

        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movieTitle", movie.getTitle());
            intent.putExtra("movieYear", "Release Year:\n" + movie.getYear());
            intent.putExtra("movieRating", "IMDb Rating:\n" + movie.getRating());
            intent.putExtra("movieDescription", "Description:\n" + movie.getDescription());
            intent.putExtra("movieRating", "IMDb Rating:\n" + movie.getRating());
            intent.putExtra("movieDirector", "Director:\n" + movie.getDirector());
            intent.putExtra("moviePosterUrl", movie.getPosterUrl());
            context.startActivity(intent); // Start the MovieDetailsActivity
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
