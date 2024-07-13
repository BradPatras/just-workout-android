package io.github.bradpatras.justworkout.database.workout

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface WorkoutDao {
    @Delete
    suspend fun delete(workout: WorkoutEntity)

    @Transaction
    @Query("SELECT * FROM workout WHERE workoutId = :id")
    fun get(id: UUID): Flow<WorkoutWithTagsAndExercises>

    @Transaction
    @Query("SELECT * FROM workout")
    fun getAll(): Flow<List<WorkoutWithTagsAndExercises>>

    @Update
    suspend fun createOrUpdate(vararg workouts: WorkoutEntity)
}