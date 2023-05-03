package io.github.bradpatras.justworkout.database.musclegroup

import androidx.room.*

@Dao
interface MuscleGroupDao {
    @Insert
    suspend fun insert(vararg muscleGroups: MuscleGroupEntity)

    @Delete
    suspend fun delete(muscleGroup: MuscleGroupEntity)

    @Query("SELECT * FROM muscle_group")
    suspend fun getAll(): List<MuscleGroupEntity>

    @Update
    suspend fun update(vararg muscleGroups: MuscleGroupEntity)
}