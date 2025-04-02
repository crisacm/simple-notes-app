package com.github.crisacm.data.repository

import com.github.crisacm.data.database.daos.NoteDao
import com.github.crisacm.data.mapper.asDomain
import com.github.crisacm.data.mapper.asEntity
import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
  private val noteDao: NoteDao,
) : NoteRepository {
  override suspend fun addNote(note: Note): Long = noteDao.insert(note.asEntity())

  override suspend fun getBy(id: Long): Note? = noteDao.getById(id)?.asDomain()

  override fun getAllNotes(): Flow<List<Note>> =
    noteDao.getAllFlow().map { list ->
      list.map { it.asDomain() }
    }

  override suspend fun deleteNote(note: Note) {
    noteDao.delete(note.asEntity())
  }

  override suspend fun updateNote(note: Note) {
    noteDao.update(note.asEntity())
  }
}
