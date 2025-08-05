package io.github.bradpatras.justworkout.ui.exercises.select

import io.github.bradpatras.justworkout.models.Exercise
import java.io.Serializable

data class ExerciseSelectScreenNavArgs(
    val selectedExercises: Array<Exercise>
) : Serializable {

    // Auto-generated overrides due to Array type property
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExerciseSelectScreenNavArgs

        return selectedExercises.contentEquals(other.selectedExercises)
    }

    override fun hashCode(): Int {
        return selectedExercises.contentHashCode()
    }
}