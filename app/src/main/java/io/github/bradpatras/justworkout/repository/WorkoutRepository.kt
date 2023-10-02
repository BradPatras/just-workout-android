package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    @WorkerThread
    fun fetchWorkouts(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<List<Workout>>

    @WorkerThread
    fun deleteWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun createOrUpdateWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>
}