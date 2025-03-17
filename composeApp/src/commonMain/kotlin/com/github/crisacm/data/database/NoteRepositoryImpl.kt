package com.github.crisacm.data.database

import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.repository.NoteRepository

class NoteRepositoryImpl(
  private val noteDao: NoteDao,
) : NoteRepository {
  override suspend fun addNote(note: Note) {
    noteDao.insert(note.mapToEntity())
  }
}

fun Note.mapToEntity(): NoteEntity =
  NoteEntity(
    id = id.toLong(),
    title = title,
  )
