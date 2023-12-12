package com.test.application.local_data.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM FavoriteMovieEntity")
    fun all() : List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (entity: FavoriteMovieEntity)

    @Query("SELECT * FROM FavoriteMovieEntity WHERE name LIKE :name")
    fun getDataByMovieName (name: String?) : FavoriteMovieEntity

    @Query ("DELETE FROM FavoriteMovieEntity WHERE kinopoiskId = :id")
    fun deleteById(id: Int?)

    @Update
    fun update (entity: FavoriteMovieEntity)

    @Query("SELECT COUNT(*) > 0 FROM FavoriteMovieEntity WHERE kinopoiskId = :id")
    suspend fun checkExists(id: Int): Boolean
}