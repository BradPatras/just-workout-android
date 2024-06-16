package io.github.bradpatras.justworkout.ui.exercises.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.ExerciseEditScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ExerciseEditViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val navArgs: ExerciseEditScreenNavArgs = ExerciseEditScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        ExerciseEditUiState(id = navArgs.id, description = "", isLoading = false, tags = emptyList(), title = "", isNew = navArgs.isNew)
    )
    val uiState: StateFlow<ExerciseEditUiState> = _uiState.asStateFlow()

    init {
        if (!navArgs.isNew) {
            fetchExercise(navArgs.id)
        }
    }

    private fun fetchExercise(id: UUID) {
        viewModelScope.launch {
            _uiState.emit(
                _uiState.value.copy(isLoading = true)
            )

            val exercise = exerciseRepository.fetchExercise(id = id).first()

            _uiState.emit(
                ExerciseEditUiState(
                    id = exercise.id,
                    description = exercise.description,
                    isLoading = false,
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
                    tags = _uiState.value.tags,
                    title = _uiState.value.title
                )
            )
                .single()
        }
    }

    fun onTagsSelectionChanged(tags: List<Tag>) {
        _uiState.value = _uiState.value.copy(tags = tags)
    }
}