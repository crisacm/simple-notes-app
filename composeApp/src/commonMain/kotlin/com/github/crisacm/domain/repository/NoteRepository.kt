package com.github.crisacm.domain.repository

import com.github.crisacm.domain.model.Note

interface NoteRepository {
  suspend fun addNote(note: Note)
}
