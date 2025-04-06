package com.github.crisacm.domain.useCases.note

import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(
  private val noteRepository: NoteRepository,
) {
  operator fun invoke(): Flow<List<Note>> = noteRepository.getAllNotes()
}
