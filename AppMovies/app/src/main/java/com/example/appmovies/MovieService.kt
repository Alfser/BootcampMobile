package com.example.appmovies

import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    // API https://raw.githubusercontent.com/natanfelipe/FilmesFlixJson/master/moviesList

    @GET("natanfelipe/FilmesFlixJson/master/moviesList")
    fun getAllMovies(): Call<List<Movie>>
}