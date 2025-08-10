package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Workout
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface WorkoutRepository {
    @WorkerThread
    fun fetchWorkout(id: UUID): Flow<Workout>

    @WorkerThread
    fun fetchWorkouts(): Flow<List<Workout>>

    @WorkerThread
    suspend fun deleteWorkout(workout: Workout)

    @WorkerThread
    suspend fun createOrUpdateWorkout(workout: Workout)

    @WorkerThread
    suspend fun deleteWorkoutsByIds(ids: List<UUID>)

}