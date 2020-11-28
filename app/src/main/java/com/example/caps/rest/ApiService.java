package com.example.caps.rest;

import com.example.caps.model.RoodMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    public static String URLIMAGE = "https://image.tmdb.org/t/p/w780/";
    public static String URLFILM = "https://www.themoviedb.org/movie/";
    @GET("movie/popular?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US&page=1")
    Call<RoodMovieModel>getData();


}
