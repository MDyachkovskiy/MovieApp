package com.example.kotlin_movieapp.model.room.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM FavoriteMovieEntity")
    fun all() : List<FavoriteMovieEntity>

    @Query("SELECT * FROM FavoriteMovieEntity WHERE kinopoiskId LIKE :id")
    fun getMoviebyId (id: Long) : FavoriteMovieEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (entity: FavoriteMovieEntity)

    @Update
    fun update (entity: FavoriteMovieEntity)

    @Delete
    fun delete (entity: FavoriteMovieEntity)

    @Query ("DELETE FROM FavoriteMovieEntity WHERE kinopoiskId = :id")
    fun deleteById(id: Int?)
}