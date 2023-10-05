package io.github.bradpatras.justworkout.ui.exercises.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    repository: ExerciseRepository,
    val uuidProvider: UuidProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExerciseListUiState())
    val uiState: StateFlow<ExerciseListUiState> = _uiState.asStateFlow()

    init {
        repository.fetchExercises()
            .distinctUntilChanged()
            .onEach { exercises ->
                _uiState.value = ExerciseListUiState(exercises)
            }
            .launchIn(viewModelScope)
    }
}