package com.example.profilescreen

import java.sql.Date

data class User(
    val name:String,
    val BirthDate: Date,
    val email:String,
    val photo: String,
    val password: String
    )
