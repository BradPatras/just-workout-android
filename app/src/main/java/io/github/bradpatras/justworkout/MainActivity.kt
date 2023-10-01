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
import io.github.bradpatras.justworkout.repository.ExerciseRepository
import io.github.bradpatras.justworkout.repository.TagRepository
import io.github.bradpatras.justworkout.ui.MainScreen
import io.github.bradpatras.justworkout.ui.theme.JustWorkoutTheme
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var exerciseRepository: ExerciseRepository
    @Inject lateinit var tagRepository: TagRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        lifecycleScope.launch {
            (Mocks.mockTagList1 + Mocks.mockTagsList2).forEach {
                tagRepository.createTag(it, {}, {}).single()
                Timber.i("creating tag")
            }

            Mocks.mockExerciseList.forEach {
                Timber.i("creating exercise")
                exerciseRepository.createOrUpdateExercise(it, {},  {}).single()
            }
        }
    }
}