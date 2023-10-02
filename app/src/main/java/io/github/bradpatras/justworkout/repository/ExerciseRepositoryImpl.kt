package io.github.bradpatras.justworkout.repository

import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRef
import io.github.bradpatras.justworkout.database.exercise.ExerciseTagCrossRefDao
import io.github.bradpatras.justworkout.database.exercise.asExercise
import io.github.bradpatras.justworkout.database.exercise.asExerciseWithTags
import io.github.bradpatras.justworkout.di.IoDispatcher
import io.github.bradpatras.justworkout.models.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import java.util.UUID
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val exerciseTagCrossRefDao: ExerciseTagCrossRefDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ExerciseRepository {
    override fun fetchExercise(id: UUID) = exerciseDao
        .get(id)
        .map { it.asExercise() }
        .flowOn(ioDispatcher)

    override fun fetchExercises() = exerciseDao
        .getAll()
        .map { list -> list.map { it.asExercise() } }
        .flowOn(ioDispatcher)

    override fun createOrUpdateExercise(exercise: Exercise) = flow<Unit> {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .createOrUpdate(exercises = arrayOf(entity.exercise))

        exerciseTagCrossRefDao.deleteByExercise(exercise.id)
        exerciseTagCrossRefDao.insert(
            exercise.tags.map {
                ExerciseTagCrossRef(exerciseId = exercise.id, tagId = it.id)
            }
        )

        emit(Unit)
    }
        .flowOn(ioDispatcher)

    override fun deleteExercise(exercise: Exercise) = flow<Unit> {
        val entity = exercise.asExerciseWithTags()
        exerciseDao
            .delete(exercise = entity.exercise)

        exerciseTagCrossRefDao
            .deleteByExercise(exerciseId = exercise.id)

        emit(Unit)
    }
        .flowOn(ioDispatcher)
}