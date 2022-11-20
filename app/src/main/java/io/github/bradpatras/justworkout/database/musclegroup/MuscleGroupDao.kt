package io.github.bradpatras.justworkout.database.musclegroup

import androidx.room.*

@Dao
interface MuscleGroupDao {
    @Insert
    fun insert(vararg muscleGroup: MuscleGroupEntity)

    @Delete
    fun delete(muscleGroup: MuscleGroupEntity)

    @Query("SELECT * FROM muscle_group")
    fun getAll(): List<MuscleGroupEntity>

    @Update
    fun update(vararg muscleGroups: MuscleGroupEntity)
}