package com.example.appmovies

import android.transition.CircularPropagation
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.appmovies.databinding.RecyclerViewItemBinding

class MovieAdapter(): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private var movies: List<Movie> = listOf()

    inner class MovieViewHolder(
        val binding: RecyclerViewItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.tvTitleVideo.text = movies[position].title
        holder.binding.imageVideo.load(movies[position].image){
            placeholder(R.drawable.ic_image_default)
            fallback(R.drawable.ic_image_default)
            error(R.drawable.ic_image_error)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setMovieList(movies: List<Movie>) {
        this.movies = movies
    }
}