package io.github.bradpatras.justworkout.ui.exercises.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.ExerciseDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.ui.exercises.edit.ExerciseEditUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val navArgs: ExerciseDetailsScreenNavArgs = ExerciseDetailsScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        ExerciseDetailsUiState(
            Exercise(
                id = navArgs.id,
                title = "",
                tags = emptyList(),
                description = ""
            )
        )
    )
    val uiState: StateFlow<ExerciseDetailsUiState> = _uiState.asStateFlow()

    init {
        fetchExercise(navArgs.id)
    }

    private fun fetchExercise(id: UUID) {
        viewModelScope.launch {
            val exercise = exerciseRepository.fetchExercise(id = id).first()

            _uiState.emit(
                ExerciseDetailsUiState(exercise)
            )
        }
    }

    fun reloadExercise() {
        fetchExercise(navArgs.id)
    }
}