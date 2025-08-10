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
    @Query("SELECT * FROM workout ORDER BY title COLLATE NOCASE ASC")
    fun getAll(): Flow<List<WorkoutWithTagsAndExercises>>

    @Upsert
    suspend fun createOrUpdate(vararg workouts: WorkoutEntity)

    @Query("DELETE FROM workout WHERE workoutId IN (:ids)")
    suspend fun delete(ids: List<UUID>)
}