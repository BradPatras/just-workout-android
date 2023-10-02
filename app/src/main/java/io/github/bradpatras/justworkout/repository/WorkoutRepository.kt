package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    @WorkerThread
    fun fetchWorkouts(): Flow<List<Workout>>

    @WorkerThread
    fun deleteWorkout(workout: Workout): Flow<Unit>

    @WorkerThread
    fun createOrUpdateWorkout(workout: Workout): Flow<Unit>
}