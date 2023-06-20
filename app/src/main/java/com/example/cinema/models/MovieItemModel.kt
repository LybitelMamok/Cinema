package com.example.cinema.models



import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "movie_kino")
data class MovieItemModel(
    @PrimaryKey(autoGenerate = true)

    val id: Int,

    val overview: String,

    val poster_path: String,

    val release_date: String,

    val title: String,

    ) : Serializable