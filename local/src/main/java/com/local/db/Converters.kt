package com.local.db

import androidx.room.TypeConverter
import java.math.BigInteger
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromStringToBigInteger(stringValue: String?): BigInteger? {
        return stringValue?.toBigInteger()
    }

    @TypeConverter
    fun bigIntegerToString(value: BigInteger?): String? {
        return value?.toString()
    }
}