package com.example.appmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

class MovieListViewModel: ViewModel() {

    companion object{
        const val TAG = "MovieLisViewModel"
    }

    private val movies = listOf(
        Movie(id = 0L, title = "Titanic"),
        Movie(id = 0L, title = "Independence Day"),
        Movie(id = 0L, title = "Arrival")
    )

    private val movieRestApiTask = MovieRestApiTask()
    private val movieRepository = MovieRepository(movieRestApiTask)

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    fun init(){
        getAllMovies()
    }

    private fun getAllMovies(){

        Thread{
            try {
                _movieList.postValue(movieRepository.getAllMovies())

            }catch (ex: Exception){
                Log.d(TAG, ex.message.toString())
            }

        }.start()
    }
}