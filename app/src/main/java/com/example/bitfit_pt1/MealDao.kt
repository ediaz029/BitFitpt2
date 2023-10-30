package com.example.bitfit_pt1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meal_table")
    fun getAll(): Flow<List<MealEntity>>

    @Insert
    fun insert(meal: MealEntity)
    fun insertAll(meals: List<MealEntity>)

    @Query("DELETE FROM meal_table")
    fun deleteAll()
}