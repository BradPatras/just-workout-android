package io.github.bradpatras.justworkout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.bradpatras.justworkout.models.DefaultModels
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.repository.WorkoutRepository
import io.github.bradpatras.justworkout.ui.MainScreen
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var exerciseRepository: ExerciseRepository
    @Inject lateinit var tagRepository: TagRepository
    @Inject lateinit var workoutRepository: WorkoutRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            JustWorkoutTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }

//        lifecycleScope.launch {
//            for (tag in DefaultModels.tags) {
//                tagRepository.createTag(tag)
//            }
//
//            for (exercise in DefaultModels.exercises) {
//                exerciseRepository.createOrUpdateExercise(exercise)
//            }
//
//            for (workout in DefaultModels.workouts) {
//                workoutRepository.createOrUpdateWorkout(workout)
//            }
//        }
    }
}