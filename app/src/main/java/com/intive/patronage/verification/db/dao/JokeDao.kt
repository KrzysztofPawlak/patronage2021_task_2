package com.intive.patronage.verification.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.intive.patronage.verification.db.model.JokeEntry
import io.reactivex.rxjava3.core.Single

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(jokeEntry: JokeEntry)

    @Query("select * from joke_table")
    fun getAll(): Single<List<JokeEntry>>
}