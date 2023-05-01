package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.workout.WorkoutDao
import io.github.bradpatras.justworkout.models.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class WorkoutRepositoryImpl constructor(
    private val workoutDao: WorkoutDao,
    private val ioDispatcher: CoroutineDispatcher
): WorkoutRepository {
    override fun fetchWorkouts(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<List<Workout>> {
        TODO("Not yet implemented")
    }

    override fun updateWorkout(
        workout: Workout, onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun createWorkout(
        workout: Workout,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}