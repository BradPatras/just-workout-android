package io.github.bradpatras.justworkout.ui.workouts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import io.github.bradpatras.justworkout.ui.exercises.list.ExerciseListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WorkoutListViewModel @Inject constructor(
    repository: WorkoutRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutListUiState(isLoading = true))
    val uiState: StateFlow<WorkoutListUiState> = _uiState.asStateFlow()

    init {
        repository.fetchWorkouts()
            .distinctUntilChanged()
            .onEach { workouts ->
                _uiState.value = WorkoutListUiState(isLoading = false, workouts)
            }
            .launchIn(viewModelScope)
    }
}