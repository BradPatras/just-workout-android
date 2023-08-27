package io.github.bradpatras.justworkout.database

import androidx.room.TypeConverter
import java.util.UUID

class UuidTypeConverter {
    @TypeConverter
    fun uuidToString(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun stringToUuid(string: String): UUID {
        return UUID.fromString(string)
    }
}