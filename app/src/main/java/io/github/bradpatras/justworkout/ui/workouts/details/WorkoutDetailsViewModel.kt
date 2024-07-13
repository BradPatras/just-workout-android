package io.github.bradpatras.justworkout.ui.workouts.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.generated.destinations.WorkoutDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Workout
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val navArgs: WorkoutDetailsScreenNavArgs = WorkoutDetailsScreenDestination.argsFrom(savedStateHandle)
    private val _uiState = MutableStateFlow(
        WorkoutDetailsUiState(
            Workout(
                id = navArgs.id,
                title = "",
                tags = emptyList(),
                notes = "",
                datesCompleted = emptyList(),
                exercises = emptyList()
            )
        )
    )
    val uiState: StateFlow<WorkoutDetailsUiState> = _uiState.asStateFlow()

    init {
        fetchWorkout(navArgs.id)
    }

    private fun fetchWorkout(id: UUID) {
        viewModelScope.launch {
            val workout = workoutRepository.fetchWorkout(id = id).first()

            _uiState.emit(
                WorkoutDetailsUiState(workout)
            )
        }
    }

    fun reloadWorkout() {
        fetchWorkout(navArgs.id)
    }
}