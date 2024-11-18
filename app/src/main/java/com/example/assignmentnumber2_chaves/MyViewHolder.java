package com.example.assignmentnumber2_chaves;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView title;
    TextView year;
    TextView rating;
    TextView description;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        title = itemView.findViewById(R.id.title_txt);
        year = itemView.findViewById(R.id.year_text);
        rating = itemView.findViewById(R.id.rating_text);
        description = itemView.findViewById(R.id.movieDescription);
    }
}
