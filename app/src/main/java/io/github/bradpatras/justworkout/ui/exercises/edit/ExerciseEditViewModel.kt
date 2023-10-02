package io.github.bradpatras.justworkout.ui.exercises.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.ui.destinations.ExerciseEditScreenDestination
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ExerciseEditViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val navArgs = ExerciseEditScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        ExerciseEditUiState(description = "", tags = emptyList(), title = "", isNew = navArgs.isNew)
    )
    val uiState: StateFlow<ExerciseEditUiState> = _uiState.asStateFlow()

    init {
        if (!navArgs.isNew) {
            fetchExercise(navArgs.id)
        }
    }

    private fun fetchExercise(id: UUID) {
        viewModelScope.launch {

            val exercise = exerciseRepository.fetchExercise(id = id).single()

            _uiState.emit(
                ExerciseEditUiState(
                    description = exercise.description,
                    tags = exercise.tags,
                    title = exercise.title,
                    isNew = false
                )
            )
        }
    }

    fun onTitleChanged(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun onDescriptionChanged(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun onCheckmarkTapped() {
        viewModelScope.launch {
            exerciseRepository.createOrUpdateExercise(
                exercise = Exercise(
                    description = _uiState.value.description,
                    id = navArgs.id,
                    tags = emptyList(),
                    title = _uiState.value.title
                )
            )
                .single()
        }
    }
}