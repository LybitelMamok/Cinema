package com.example.cinema.data.retrofit.api


import com.example.cinema.models.MoviesModels
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(value = "3/movie/popular?api_key=2c665b7ef850c98c9f4ea6c281d0a48c&language=en-US&page=1")
    suspend fun getPopularMovie(): Response<MoviesModels>
}