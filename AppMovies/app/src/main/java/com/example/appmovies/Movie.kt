package com.example.appmovies

import java.sql.Date


data class Movie(
    val id: Long,
    val title: String,
    val description: String? = null,
    val image: String? = null,
    val date: Date? = null
)
