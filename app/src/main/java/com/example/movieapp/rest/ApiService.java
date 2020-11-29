package com.example.movieapp.rest;

import com.example.movieapp.model.RoodMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    public static String URLIMAGE = "https://image.tmdb.org/t/p/w780/";
    public static String URLFILM = "https://www.themoviedb.org/movie/";
    public static String MOVIE_POPULAR = "discover/movie?";
    public static String APIKEY = "api_key=859e1e2595ca61e03a724fb8889e0ddb";
    public static String LANGUAGE = "&language=en-US";
    public static String NOTIF_DATE = "&primary_release_date.gte=";
    public static String REALESE_DATE = "&primary_release_date.lte=";
    @GET("movie/popular?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US&page=1")
    Call<RoodMovieModel> getData();
}
