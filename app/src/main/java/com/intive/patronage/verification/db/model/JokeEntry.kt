package com.intive.patronage.verification.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke_table")
data class JokeEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val text2: String,
    val category: String,
    val type: String
)