package com.example.caps.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.caps.R;
import com.example.caps.model.ResultsItem;
import com.example.caps.rest.ApiService;
import com.example.caps.DetailMovie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<ResultsItem> resultsItems;
    private Context context;
    private double Rating;


    public MovieAdapter(ArrayList<ResultsItem> resultsItems, Context context) {
        this.context= context;
        this.resultsItems = resultsItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MovieAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ResultsItem data = resultsItems.get(position);

        holder.movie_title.setText(resultsItems.get(position).getTitle());
        holder.movie_overview.setText(resultsItems.get(position).getOverview());





        Glide.with(context)
                .load(ApiService.URLIMAGE +resultsItems.get(position).getPosterPath())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image)
                        .transform(new RoundedCorners(16)))
                .into(holder.iv);

        holder.cvklik.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent= new Intent(context, DetailMovie.class);
                intent.putExtra(DetailMovie.detail_key, resultsItems.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView movie_title;
        private TextView movie_overview;
        private CardView cvklik;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvklik = itemView.findViewById(R.id.cv_klik);
            movie_title = itemView.findViewById(R.id.movie_title);
            movie_overview = itemView.findViewById(R.id.movie_overview);
            iv = itemView.findViewById(R.id.iv);

        }
    }
}
