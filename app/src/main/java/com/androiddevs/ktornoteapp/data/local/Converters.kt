package com.androiddevs.ktornoteapp.data.local

import androidx.room.TypeConverter

class Converters {

    // This is a simple solution just to get owner email list from database
    @TypeConverter
    fun fromList(list: List<String>) : String {
        return list.joinToString()
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return value.split(",")
    }
}