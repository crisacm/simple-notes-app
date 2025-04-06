package com.github.crisacm.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.crisacm.data.database.entities.NoteContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteContentDao {
  @Query("SELECT * FROM note_content WHERE noteId = :noteId")
  fun getOnFlowNoteContents(noteId: Long): Flow<List<NoteContentEntity>>

  @Query("SELECT * FROM note_content WHERE noteId = :noteId")
  suspend fun getNoteContents(noteId: Long): List<NoteContentEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(noteContent: NoteContentEntity)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(noteContent: NoteContentEntity)

  @Delete
  suspend fun delete(noteContent: NoteContentEntity)
}
