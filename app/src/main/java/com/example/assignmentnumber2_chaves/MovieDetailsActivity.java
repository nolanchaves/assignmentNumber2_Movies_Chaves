package com.example.assignmentnumber2_chaves;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {
    private TextView titleTextView, yearTextView, descriptionTextView, directorTextView, ratingTextView;
    private ImageView posterImageView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        titleTextView = findViewById(R.id.title_txt);
        yearTextView = findViewById(R.id.year_text);
        descriptionTextView = findViewById(R.id.movieDescription);
        directorTextView = findViewById(R.id.movieDirector);
        posterImageView = findViewById(R.id.imageview);
        ratingTextView = findViewById(R.id.movieRating);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("movieTitle");
        String year = intent.getStringExtra("movieYear");
        String description = intent.getStringExtra("movieDescription");
        String posterUrl = intent.getStringExtra("moviePosterUrl");
        String director = intent.getStringExtra("movieDirector");
        String rating = intent.getStringExtra("movieRating");

        titleTextView.setText(title);
        yearTextView.setText(year);
        ratingTextView.setText(rating);
        descriptionTextView.setText(description);
        directorTextView.setText(director);
        backButton.setText(R.string.returnButton);

        // Load the movie poster using Glide
        Glide.with(this).load(posterUrl).into(posterImageView);

        // Handle back button click and go back to the previous activity
        backButton.setOnClickListener(v -> finish());
    }
}