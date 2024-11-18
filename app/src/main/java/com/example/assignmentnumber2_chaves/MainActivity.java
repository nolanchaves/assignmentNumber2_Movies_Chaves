package com.example.assignmentnumber2_chaves;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private TextView title;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ApiClient apiClient;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.searchBox);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.textView);

        //Set String Values
        searchButton.setText(R.string.searchButton);
        title.setText(R.string.mainTitle);
        searchField.setHint(R.string.searchInputPlaceholder);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiClient = new ApiClient();
        movieList = new ArrayList<>();
        myAdapter = new MyAdapter(movieList, this);
        recyclerView.setAdapter(myAdapter);

        searchButton.setOnClickListener(v -> {
            String query = searchField.getText().toString().trim();
            if (!query.isEmpty()) {
                fetchMovies(query);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a movie title", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchMovies(String query) {
        apiClient.searchMovies(query, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonResponse = response.body().string();
                    List<Movie> fetchedMovies = parseMovies(jsonResponse);
                    runOnUiThread(() -> {
                        movieList.clear();
                        movieList.addAll(fetchedMovies);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "No results found", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private List<Movie> parseMovies(String jsonResponse) {
        List<Movie> movies = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray searchArray = jsonObject.getJSONArray("Search");
            for (int i = 0; i < searchArray.length(); i++) {
                JSONObject movieObject = searchArray.getJSONObject(i);
                String title = movieObject.getString("Title");
                String year = movieObject.getString("Year");
                String posterUrl = movieObject.getString("Poster");
                String imdbID = movieObject.getString("imdbID");

                Movie movie = new Movie(title, "N/A", year, posterUrl, "No description available", "N/A");
                movies.add(movie);

                fetchMovieDetails(imdbID, movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    private void fetchMovieDetails(String imdbID, Movie movie) {
        apiClient.getMovieDetails(imdbID, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error fetching details", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String jsonResponse = response.body().string();
                        JSONObject movieDetails = new JSONObject(jsonResponse);

                        String description = movieDetails.optString("Plot", "No description available");
                        String rating = movieDetails.optString("imdbRating", "N/A");
                        String director = movieDetails.optString("Director", "N/A");

                        movie.setDescription(description);
                        movie.setRating(rating);
                        movie.setDirector(director);

                        runOnUiThread(() -> myAdapter.notifyDataSetChanged());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}