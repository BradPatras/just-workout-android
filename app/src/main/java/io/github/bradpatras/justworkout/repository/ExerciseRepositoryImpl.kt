package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRef
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRefDao
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.database.exercise.asExerciseWithTags
import io.github.bradpatras.justworkout.database.workout.WorkoutExerciseCrossRef
import io.github.bradpatras.justworkout.database.workout.WorkoutExerciseCrossRefDao
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val exerciseTagCrossRefDao: ExerciseTagCrossRefDao,
    private val workoutExerciseCrossRefDao: WorkoutExerciseCrossRefDao,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ExerciseRepository {
    override fun fetchExercise(id: UUID) = exerciseDao
        .get(id)
        .map { it.asExercise() }
        .flowOn(ioDispatcher)

    override fun fetchExercises() = exerciseDao
        .getAll()
        .map { list -> list.map { it.asExercise() } }
        .flowOn(ioDispatcher)

    override suspend fun createOrUpdateExercise(exercise: Exercise) {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .createOrUpdate(exercises = arrayOf(entity.exercise))

        exerciseTagCrossRefDao.deleteByExercise(exercise.id)
        exerciseTagCrossRefDao.insert(
            exercise.tags.map {
                ExerciseTagCrossRef(exerciseId = exercise.id, tagId = it.tagId)
            }
        )
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .delete(exercise = entity.exercise)

        exerciseTagCrossRefDao
            .deleteByExercise(exerciseId = exercise.id)
    }

    override suspend fun deleteExercisesByIds(exerciseIds: List<UUID>) {
        exerciseDao
            .deleteByIds(exerciseIds)

        exerciseTagCrossRefDao
            .deleteByExerciseIds(exerciseIds)

        workoutExerciseCrossRefDao
            .deleteByExerciseIds(exerciseIds)
    }
}