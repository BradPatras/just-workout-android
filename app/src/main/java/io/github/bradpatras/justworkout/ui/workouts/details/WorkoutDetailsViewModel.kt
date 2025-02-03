package io.github.bradpatras.justworkout.ui.workouts.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.WorkoutDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(
    workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val navArgs: WorkoutDetailsScreenNavArgs = WorkoutDetailsScreenDestination.argsFrom(savedStateHandle)

    val uiState: StateFlow<WorkoutDetailsUiState> = workoutRepository
        .fetchWorkout(navArgs.id)
        .map { workout ->
            WorkoutDetailsUiState(workout)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            WorkoutDetailsUiState(
                Workout(
                    id = navArgs.id,
                    notes = "",
                    exercises = emptyList(),
                    title = "",
                    datesCompleted = emptyList(),
                    tags = emptyList()
                )
            )
        )
}