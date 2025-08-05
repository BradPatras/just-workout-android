package io.github.bradpatras.justworkout.ui.exercises.select

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.ExerciseSelectScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.SelectableExercise
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseSelectViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {
    private val navArgs: ExerciseSelectScreenNavArgs = ExerciseSelectScreenDestination.argsFrom(savedStateHandle)
    private var allExercises = emptyList<SelectableExercise>()
    private val _uiState = MutableStateFlow(
        ExerciseSelectUiState(
            isLoading = false,
            exercises = emptyList(),
            searchQuery = ""
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
            allExercises = exercises.map {
                SelectableExercise(it, isSelected = navArgs.selectedExercises.contains(it))
            }

            _uiState.emit(
                ExerciseSelectUiState(
                    isLoading = false,
                    exercises = allExercises,
                    searchQuery = ""
                )
            )
        }
    }

    fun exerciseTapped(exercise: SelectableExercise) {
        allExercises = allExercises.toMutableList().apply {
            val index = indexOfFirst { it.id() == exercise.id() }
            val newExercise = exercise.copy(isSelected = !exercise.isSelected)
            this[index] = newExercise
        }

        _uiState.update { state ->
            val newExercises = state.exercises.toMutableList()
            val newExercise = exercise.copy(isSelected = !exercise.isSelected)
            val index = state.exercises.indexOfFirst { exercise.id() == it.id() }
            newExercises[index] = newExercise
            uiState.value.copy(exercises = newExercises)
        }
    }

    fun searchQueryChanged(query: String) {
        if (query.isEmpty()) {
            _uiState.update {
                uiState.value.copy(
                    searchQuery = query,
                    exercises = allExercises
                )
            }
        } else {
            _uiState.update {
                uiState.value.copy(
                    searchQuery = query,
                    exercises = allExercises.filter {
                        it.exercise.matchesSearchQuery(query)
                    }
                )
            }
        }
    }
}
