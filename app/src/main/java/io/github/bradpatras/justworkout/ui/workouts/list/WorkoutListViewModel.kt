package io.github.bradpatras.justworkout.ui.workouts.list

import androidx.lifecycle.ViewModel
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutListViewModel(
    repository: WorkoutRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutListUiState())
    val uiState: StateFlow<WorkoutListUiState> = _uiState.asStateFlow()
}