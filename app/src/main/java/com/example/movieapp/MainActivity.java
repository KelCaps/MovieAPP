package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.model.ResultsItem;
import com.example.movieapp.model.RoodMovieModel;
import com.example.movieapp.rest.ApiConfigServer;
import com.example.movieapp.rest.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    private ArrayList<ResultsItem> resultsItems;
    private MovieAdapter movieAdapter;
    ImageView imgNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        resultsItems = new ArrayList<>();
        getData();

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedLIstener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(),FragmentFavorite.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),FragmentAbout.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        imgNotification = findViewById(R.id.imgNotification);
        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

    }


    private void getData() {
        ApiService apiService = ApiConfigServer.getApiService();
        apiService.getData()
                .enqueue(new Callback<RoodMovieModel>() {
                    @Override
                    public void onResponse(Call<RoodMovieModel> call, Response<RoodMovieModel> response)
                    {
                        if (response.isSuccessful()) {
                            resultsItems = response.body().getResults();
                            movieAdapter = new MovieAdapter(resultsItems, getApplicationContext());
                            movieAdapter.notifyDataSetChanged();
                            rv.setAdapter(movieAdapter);
                            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }
                    @Override
                    public void onFailure(Call<RoodMovieModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rv = findViewById(R.id.rv);
    }

}