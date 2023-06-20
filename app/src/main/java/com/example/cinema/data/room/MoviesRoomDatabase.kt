package com.example.cinema.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cinema.models.MovieItemModel
import com.example.cinema.data.room.dao.MoviesDao

@Database(entities = [MovieItemModel::class], version = 3)
abstract class MoviesRoomDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

    companion object{
        private var database: MoviesRoomDatabase ?= null

        fun getInstance(context: Context): MoviesRoomDatabase{
            return if (database == null){
                database = Room
                    .databaseBuilder(context, MoviesRoomDatabase::class.java, "datadb")
                    .build()
                database as MoviesRoomDatabase
            }else{
                database as MoviesRoomDatabase

            }

        }
    }


}