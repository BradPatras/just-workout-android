package io.github.bradpatras.justworkout.ui.exercises.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.github.bradpatras.justworkout.ui.destinations.ExerciseEditScreenDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExerciseEditViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ExerciseEditScreenDestination.argsFrom(savedStateHandle)
    )
    val uiState: StateFlow<ExerciseEditUiState> = _uiState.asStateFlow()
}