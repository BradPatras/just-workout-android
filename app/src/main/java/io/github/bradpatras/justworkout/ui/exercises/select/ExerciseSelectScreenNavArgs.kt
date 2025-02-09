package io.github.bradpatras.justworkout.ui.exercises.select

import io.github.bradpatras.justworkout.models.Exercise
import java.io.Serializable

data class ExerciseSelectScreenNavArgs(
    val selectedExercises: Array<Exercise>
) : Serializable