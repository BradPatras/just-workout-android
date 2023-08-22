package io.github.bradpatras.justworkout.ui.exercises.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.github.bradpatras.justworkout.ui.destinations.ExerciseDetailsScreenDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExerciseDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ExerciseDetailsScreenDestination.argsFrom(savedStateHandle)
    )
    val uiState: StateFlow<ExerciseDetailsUiState> = _uiState.asStateFlow()
}