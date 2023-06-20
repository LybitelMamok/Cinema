package com.example.cinema.models

data class MoviesModels(
    val page: Int,
    val results: List<MovieItemModel>,
    val total_pages: Int,
    val total_results: Int
)