package com.example.caps;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caps.adapter.MovieAdapter;
import com.example.caps.model.ResultsItem;
import com.example.caps.model.RoodMovieModel;
import com.example.caps.rest.ApiConfigServer;
import com.example.caps.rest.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    private ArrayList<ResultsItem> resultsItems;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        initView();
        resultsItems = new ArrayList<>();
        getData();


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


