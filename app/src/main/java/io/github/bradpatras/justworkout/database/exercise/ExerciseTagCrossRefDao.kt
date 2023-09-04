package io.github.bradpatras.justworkout.database.exercise

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface ExerciseTagCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crossRefs: List<ExerciseTagCrossRef>)

    @Delete
    suspend fun delete(crossRef: ExerciseTagCrossRef)

    @Query("DELETE FROM exercise_tag_cross_ref WHERE exerciseId = :exerciseId")
    suspend fun deleteByExercise(exerciseId: UUID)

    @Query("SELECT * FROM exercise_tag_cross_ref")
    suspend fun getAll(): List<ExerciseTagCrossRef>

    @Update
    suspend fun update(vararg crossRefs: ExerciseTagCrossRef)
}