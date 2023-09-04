package io.github.bradpatras.justworkout.database.workout

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.util.UUID

@Dao
interface WorkoutTagCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(crossRefs: List<WorkoutTagCrossRef>)

    @Query("DELETE FROM workout_tag_cross_ref WHERE workoutId = :workoutId")
    suspend fun deleteByWorkout(workoutId: UUID)

    @Query("SELECT * FROM workout_tag_cross_ref")
    suspend fun getAll(): List<WorkoutTagCrossRef>

    @Update
    suspend fun update(vararg crossRefs: WorkoutTagCrossRef)
}