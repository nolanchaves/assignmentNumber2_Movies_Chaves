package com.example.assignmentnumber2_chaves;

import android.content.Context;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class ApiClient {
    private static final String BASE_URL = "https://www.omdbapi.com/";
    private static final String API_KEY = "cdc2bde2";
    private OkHttpClient client;

    public ApiClient() {
        client = new OkHttpClient();
    }

    // Method to search for movies by title
    public void searchMovies(String query, Callback callback) {
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder()
                .addQueryParameter("s", query)  // "s" for searching by movie title
                .addQueryParameter("type", "movie")  // Filter to only show movie results
                .addQueryParameter("apikey", API_KEY)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }

    // Method to get detailed information (description and rating) for a movie
    public void getMovieDetails(String imdbID, Callback callback) {
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder()
                .addQueryParameter("i", imdbID)  // "i" for movie IMDb ID
                .addQueryParameter("apikey", API_KEY)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
