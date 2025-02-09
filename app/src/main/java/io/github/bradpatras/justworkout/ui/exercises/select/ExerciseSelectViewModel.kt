package io.github.bradpatras.justworkout.ui.exercises.select

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.ExerciseSelectScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseSelectViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {
    private val navArgs: ExerciseSelectScreenNavArgs = ExerciseSelectScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        ExerciseSelectUiState(
            isLoading = false,
            allExercises = emptyList(),
            selectedExercises = navArgs.selectedExercises.toList()
        )
    )

    val uiState: StateFlow<ExerciseSelectUiState> = _uiState.asStateFlow()

    init {
        fetchExercises()
    }

    private fun fetchExercises() {
        viewModelScope.launch {
            _uiState.emit(uiState.value.copy(isLoading = true))

            val exercises = exerciseRepository.fetchExercises().first()

            _uiState.emit(
                ExerciseSelectUiState(
                    isLoading = false,
                    allExercises = exercises,
                    selectedExercises = uiState.value.selectedExercises
                )
            )
        }
    }

    fun exerciseTapped(exercise: Exercise) {
        val newSelection = uiState.value.selectedExercises.toMutableList()

        if (!newSelection.remove(exercise)) {
            newSelection.add(exercise)
        }

        _uiState.tryEmit(
            uiState.value.copy(
                selectedExercises = newSelection
            )
        )
    }
}
