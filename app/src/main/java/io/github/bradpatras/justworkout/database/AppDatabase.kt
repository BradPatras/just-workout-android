package io.github.bradpatras.justworkout.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.bradpatras.justworkout.database.exercise.ExerciseDao
import io.github.bradpatras.justworkout.database.exercise.ExerciseEntity
import io.github.bradpatras.justworkout.database.tag.TagDao
import io.github.bradpatras.justworkout.database.tag.TagEntity
import io.github.bradpatras.justworkout.database.workout.WorkoutDao
import io.github.bradpatras.justworkout.database.workout.WorkoutEntity

@Database(
    entities = [
        ExerciseEntity::class,
        TagEntity::class,
        WorkoutEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun tagDao(): TagDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        private const val NAME = "justWorkoutDatabase"

        fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}