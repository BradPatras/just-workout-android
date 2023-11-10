package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.workout.WorkoutDao
import io.github.bradpatras.justworkout.database.workout.WorkoutExerciseCrossRef
import io.github.bradpatras.justworkout.database.workout.WorkoutExerciseCrossRefDao
import io.github.bradpatras.justworkout.database.workout.WorkoutTagCrossRef
import io.github.bradpatras.justworkout.database.workout.WorkoutTagCrossRefDao
import io.github.bradpatras.justworkout.database.workout.asWorkout
import io.github.bradpatras.justworkout.database.workout.asWorkoutEntity
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Workout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val workoutExerciseCrossRefDao: WorkoutExerciseCrossRefDao,
    private val workoutTagCrossRefDao: WorkoutTagCrossRefDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): WorkoutRepository {
    override fun fetchWorkouts() = workoutDao
        .getAll()
        .map { list -> list.map { it.asWorkout() } }
        .flowOn(ioDispatcher)

    override fun createOrUpdateWorkout(workout: Workout ) = flow<Unit> {
        val entity = workout.asWorkoutEntity()
        workoutDao
            .createOrUpdate(workouts = arrayOf(entity.workout))

        workoutExerciseCrossRefDao
            .deleteByWorkout(workoutId = workout.id)
        workoutExerciseCrossRefDao
            .insert(
                entity.exercises.map {
                    WorkoutExerciseCrossRef(workoutId = workout.id, it.exercise.exerciseId)
                }
            )

        workoutTagCrossRefDao
            .deleteByWorkout(workoutId = workout.id)
        workoutTagCrossRefDao
            .insert(
                entity.tags.map {
                    WorkoutTagCrossRef(workoutId = workout.id, it.title)
                }
            )

        emit(Unit)
    }
        .flowOn(ioDispatcher)

    override fun deleteWorkout(workout: Workout) = flow<Unit> {
        val entity = workout.asWorkoutEntity()
        workoutDao
            .delete(workout = entity.workout)

        workoutExerciseCrossRefDao.deleteByWorkout(workoutId = workout.id)
        workoutTagCrossRefDao.deleteByWorkout(workoutId = workout.id)

        emit(Unit)
    }
        .flowOn(ioDispatcher)
}