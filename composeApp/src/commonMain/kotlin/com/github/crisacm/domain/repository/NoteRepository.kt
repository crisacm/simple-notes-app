package com.github.crisacm.domain.repository

import com.github.crisacm.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
  fun getAllNotes(): Flow<List<Note>>

  suspend fun getBy(id: Long): Note?

  suspend fun addNote(note: Note): Long

  suspend fun deleteNote(note: Note)

  suspend fun updateNote(note: Note)
}
