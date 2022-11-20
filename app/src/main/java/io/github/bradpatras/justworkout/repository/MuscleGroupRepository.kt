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
        tag: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    ): Flow<Unit>

    @WorkerThread
    fun deleteMuscleGroup(
        tag: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    )

    @WorkerThread
    fun createMuscleGroup(
        tag: MuscleGroup,
        onComplete: () -> Unit,
        onError: (Error) -> Unit
    )
}