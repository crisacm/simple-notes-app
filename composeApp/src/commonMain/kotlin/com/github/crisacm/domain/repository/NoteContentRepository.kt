package com.github.crisacm.domain.repository

import com.github.crisacm.domain.model.NoteContent
import kotlinx.coroutines.flow.Flow

interface NoteContentRepository {
  fun getOnFlowNoteContents(noteId: Long): Flow<List<NoteContent>>

  suspend fun getNoteContents(noteId: Long): List<NoteContent>

  suspend fun addNoteContent(noteContent: NoteContent)

  suspend fun updateNoteContent(noteContent: NoteContent)

  suspend fun deleteNoteContent(noteContent: NoteContent)
}
