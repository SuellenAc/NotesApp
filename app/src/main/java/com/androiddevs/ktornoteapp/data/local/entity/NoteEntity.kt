package com.androiddevs.ktornoteapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.util.UUID

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val date: Long,
    val owners: List<String>,
    val color: String,
    @Expose(deserialize = false, serialize = false)
    val isSynced: Boolean = false,
)
