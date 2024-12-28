package io.github.bradpatras.justworkout.ui.exercises.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    val exerciseRepository: ExerciseRepository,
    val tagRepository: TagRepository,
    val uuidProvider: UuidProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ExerciseListUiState(isLoading = true))
    val uiState: StateFlow<ExerciseListUiState> = _uiState.asStateFlow()

    init {
        exerciseRepository.fetchExercises()
            .distinctUntilChanged()
            .onEach { exercises ->
                _uiState.value = ExerciseListUiState(
                    exercises,
                    tagFilter = emptyList(),
                    isLoading = false
                )
            }
            .launchIn(viewModelScope)
    }

    fun onTagFilterSelected(tags: List<Tag>) {
        exerciseRepository.fetchExercises()
            .distinctUntilChanged()
            .onEach { exercises ->
                val filteredExercises = getFilteredExercises(exercises, tags)

                _uiState.value = ExerciseListUiState(
                    filteredExercises,
                    tagFilter = tags,
                    isLoading = false
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getFilteredExercises(exercises: List<Exercise>, tagFilter: List<Tag>): List<Exercise> {
        if (tagFilter.isNotEmpty()) {
            val exercisesWithTags = exercises.filter { it.tags.isNotEmpty() }
            // Calculate scores for each item
            val scoredExercises = exercisesWithTags.map { exercise ->
                val score = exercise.tags.intersect(tagFilter).size
                Pair(exercise, score)
            }

            // Sort items by score in descending order
            return scoredExercises
                .sortedByDescending { it.second }
                .map { it.first }
        } else {
            return exercises
        }
    }
}