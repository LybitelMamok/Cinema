package com.example.cinema.screens.main

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.MAIN
import com.example.cinema.REALISATION
import com.example.cinema.data.retrofit.RetrofitRepository
import com.example.cinema.models.MoviesModels
import com.example.cinema.data.room.MoviesRoomDatabase
import com.example.cinema.data.room.repository.MoviesRepositoryRealization
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RetrofitRepository()

    val myMovies: MutableLiveData<Response<MoviesModels>> = MutableLiveData()
    val context = application

    fun getMoviesRetrofit(){
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
            }catch (e:Exception){
                Log.e("ERROR", e.message.toString())
            }

        }
    }

    fun initDatabase(){
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALISATION = MoviesRepositoryRealization(daoMovie)

    }
}