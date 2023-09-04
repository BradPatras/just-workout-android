package io.github.bradpatras.justworkout.ui.exercises.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val repository: ExerciseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExerciseListUiState())
    val uiState: StateFlow<ExerciseListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.fetchExercises(
                onComplete = { },
                onError = { }
            )
                .collect { exercises ->
                    _uiState.emit(ExerciseListUiState(exercises))
                }
        }
    }
}