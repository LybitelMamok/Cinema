package com.example.cinema.data.retrofit

import com.example.cinema.data.retrofit.api.RetrofitInstance
import com.example.cinema.models.MoviesModels
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModels>{
        return RetrofitInstance.api.getPopularMovie()
    }
}