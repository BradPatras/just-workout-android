package io.github.bradpatras.justworkout.ui.exercises.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bradpatras.justworkout.models.Exercise
import io.github.bradpatras.justworkout.models.Tag
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.utility.UuidProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    exerciseRepository: ExerciseRepository,
    tagRepository: TagRepository,
    val uuidProvider: UuidProvider,
) : ViewModel() {
    private val tagFilter = MutableStateFlow<List<Tag>>(emptyList())

    val uiState: StateFlow<ExerciseListUiState> = combine(
        exerciseRepository.fetchExercises().distinctUntilChanged(),
        tagRepository.fetchTags().distinctUntilChanged(),
        tagFilter.asStateFlow()
    ) { exercises, tags, currentTagFilter ->
        val filteredExercises = getFilteredExercises(exercises, currentTagFilter)
        ExerciseListUiState(
            exercises = filteredExercises,
            tagFilter = currentTagFilter,
            tags = tags,
            isLoading = false
        )
    }
        .flowOn(Dispatchers.Default)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ExerciseListUiState(isLoading = true)
        )

    fun onTagFilterSelected(tags: List<Tag>) {
        tagFilter.value = tags
    }

    private fun getFilteredExercises(
        exercises: List<Exercise>,
        tagFilter: List<Tag>
    ): List<Exercise> {
        if (tagFilter.isEmpty()) {
            return exercises
        }

        // Use a sequence for lazy evaluation
        return exercises.asSequence()
            .filter { it.tags.isNotEmpty() }
            .map { exercise ->
                val score = exercise.tags.intersect(tagFilter.toSet()).size
                Pair(exercise, score)
            }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .map { it.first }
            .toList()
    }
}