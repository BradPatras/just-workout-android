package io.github.bradpatras.justworkout.ui.exercises.list

import androidx.lifecycle.ViewModel
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExerciseListViewModel(
    repository: ExerciseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExerciseListUiState())
    val uiState: StateFlow<ExerciseListUiState> = _uiState.asStateFlow()
}