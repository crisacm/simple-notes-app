package com.github.crisacm.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.crisacm.data.database.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
  @Query("SELECT * FROM notes ORDER BY id DESC")
  fun getAllFlow(): Flow<List<NoteEntity>>

  @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
  suspend fun getById(id: Long): NoteEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(note: NoteEntity): Long

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(note: NoteEntity)

  @Delete
  suspend fun delete(note: NoteEntity)
}
