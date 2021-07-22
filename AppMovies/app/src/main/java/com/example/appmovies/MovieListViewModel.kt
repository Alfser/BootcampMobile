package com.example.appmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieListViewModel: ViewModel() {

    private val movies = listOf(
        Movie(id = 0L, title = "Titanic"),
        Movie(id = 0L, title = "Independence Day"),
        Movie(id = 0L, title = "Arrival")
    )

    private val _movieList = MutableLiveData<List<Movie>>()

    val movieList: LiveData<List<Movie>>
        get() = _movieList

    fun init(){
        getAllMovies()
    }

    private fun getAllMovies(){
        _movieList.value =  movies
    }
}