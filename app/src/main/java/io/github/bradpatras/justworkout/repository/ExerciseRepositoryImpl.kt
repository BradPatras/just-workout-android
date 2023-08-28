package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRef
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRefDao
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.database.exercise.asExerciseWithTags
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val exerciseTagCrossRefDao: ExerciseTagCrossRefDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ExerciseRepository {
    override fun fetchExercises(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<List<Exercise>> {
        exerciseDao
            .getAll()
            .map { it.asExercise() }
            .also { emit(it) }
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun updateExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .update(exercises = arrayOf(entity.exercise))

        exerciseTagCrossRefDao.deleteByExercise(exercise.id)
        exerciseTagCrossRefDao.insert(
            exercise.tags.map {
                ExerciseTagCrossRef(exerciseId = exercise.id, tagId = it.id)
            }
        )

        emit(Unit)
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun deleteExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .delete(exercise = entity.exercise)

        exerciseTagCrossRefDao
            .deleteByExercise(exerciseId = exercise.id)

        emit(Unit)
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    override fun createExercise(
        exercise: Exercise,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ) = flow<Unit> {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .insert(exercises = arrayOf(entity.exercise))

        exerciseTagCrossRefDao.insert(
            exercise.tags.map {
                ExerciseTagCrossRef(exerciseId = exercise.id, tagId = it.id)
            }
        )

        emit(Unit)
    }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}