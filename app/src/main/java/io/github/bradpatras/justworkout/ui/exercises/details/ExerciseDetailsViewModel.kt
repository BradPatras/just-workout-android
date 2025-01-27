package io.github.bradpatras.justworkout.ui.exercises.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.ExerciseDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.ui.exercises.edit.ExerciseEditUiState
import io.github.bradpatras.justworkout.ui.exercises.list.ExerciseListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val navArgs: ExerciseDetailsScreenNavArgs = ExerciseDetailsScreenDestination.argsFrom(savedStateHandle)

    val uiState: StateFlow<ExerciseDetailsUiState> = exerciseRepository
        .fetchExercise(navArgs.id)
        .map { ExerciseDetailsUiState(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ExerciseDetailsUiState(
                Exercise(
                    id = navArgs.id,
                    title = "",
                    tags = emptyList(),
                    description = ""
                )
            )
        )
}