package com.github.crisacm.data.repository

import com.github.crisacm.data.database.daos.NoteContentDao
import com.github.crisacm.data.mapper.asDomain
import com.github.crisacm.data.mapper.asEntity
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.repository.NoteContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteContentRepositoryImpl(
  private val noteContentDao: NoteContentDao,
) : NoteContentRepository {
  override fun getOnFlowNoteContents(noteId: Long): Flow<List<NoteContent>> =
    noteContentDao.getOnFlowNoteContents(noteId).map { list -> list.map { it.asDomain() } }

  override suspend fun getNoteContents(noteId: Long): List<NoteContent> =
    noteContentDao.getNoteContents(noteId).map { it.asDomain() }

  override suspend fun addNoteContent(noteContent: NoteContent) {
    noteContentDao.insert(noteContent.asEntity())
  }

  override suspend fun updateNoteContent(noteContent: NoteContent) {
    noteContentDao.update(noteContent.asEntity())
  }

  override suspend fun deleteNoteContent(noteContent: NoteContent) {
    noteContentDao.delete(noteContent.asEntity())
  }
}
