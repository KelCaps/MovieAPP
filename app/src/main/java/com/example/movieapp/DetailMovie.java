package com.example.movieapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieapp.model.ResultsItem;
import com.example.movieapp.rest.ApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailMovie extends AppCompatActivity {
    Toolbar toolbar;
    ImageView ivDetail, iv;
    ImageView imgCover, imgPhoto;
    TextView movie_overview, movie_title,tvName,tvRating;
    ResultsItem resultsItem;
    RatingBar ratingBar;
    String Cover, Thumbnail, movieURL, Overview,NameFilm;
    double Rating;
    ProgressDialog progressDialog;
    int Id;
    public static String detail_key = "detailMovie";
    FloatingActionButton fabShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        iv = findViewById(R.id.iv);
        imgCover = findViewById(R.id.imgCover);
        movie_title = findViewById(R.id.movie_title);
        movie_overview= findViewById(R.id.movie_overview);
        tvName = findViewById(R.id.tvName);
        tvRating = findViewById(R.id.tvRating);
        ratingBar= findViewById(R.id.ratingBar);

        resultsItem = (ResultsItem) getIntent().getParcelableExtra(this.detail_key);
        if (resultsItem != null) {

            Id = resultsItem.getId();
            NameFilm = resultsItem.getTitle();
            Cover = resultsItem.getBackdropPath();
            Thumbnail = resultsItem.getPosterPath();
            Overview = resultsItem.getOverview();
            Rating = resultsItem.getVoteAverage();
            movieURL = ApiService.URLFILM + "" + Id;


            tvName.setText(NameFilm);
            movie_title.setText(NameFilm);
            movie_overview.setText(Overview);
            movie_title.setSelected(true);
            tvName.setSelected(true);
            tvRating.setText(Rating + "/10");
            fabShare = findViewById(R.id.fab);

            float newValue = (float) Rating;

            ratingBar.setNumStars(5);
            ratingBar.setStepSize((float) 0.5);
            ratingBar.setRating(newValue / 2);


            Glide.with(this)
                    .load(ApiService.URLIMAGE + Cover)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCover);

            Glide.with(this)
                    .load(ApiService.URLIMAGE + resultsItem.getPosterPath())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv);

        }
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String subject = resultsItem.getTitle();
                String description = resultsItem.getOverview();
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                shareIntent.putExtra(Intent.EXTRA_TEXT, subject + "\n\n" + description + "\n\n" + movieURL);
                startActivity(Intent.createChooser(shareIntent, "Bagikan dengan :"));
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
