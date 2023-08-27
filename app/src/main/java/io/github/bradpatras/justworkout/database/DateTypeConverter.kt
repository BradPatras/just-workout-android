package io.github.bradpatras.justworkout.database

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTimestampListString(value: String): List<Date> {
        return value
            .split(",")
            .mapNotNull { it.toLongOrNull() }
            .mapNotNull { fromTimestamp(it) }
    }

    @TypeConverter
    fun dateListToTimestampList(dates: List<Date>): String {
        return dates
            .mapNotNull { dateToTimestamp(it) }
            .joinToString(",")
    }
}