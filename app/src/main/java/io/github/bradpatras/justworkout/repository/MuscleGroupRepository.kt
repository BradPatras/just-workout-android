package io.github.bradpatras.justworkout.repository

import androidx.annotation.WorkerThread
import io.github.bradpatras.justworkout.models.MuscleGroup
import kotlinx.coroutines.flow.Flow

interface MuscleGroupRepository {
    @WorkerThread
    fun fetchMuscleGroups(
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<List<MuscleGroup>>

    @WorkerThread
    fun updateMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun deleteMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun createMuscleGroup(
        muscleGroup: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>
}