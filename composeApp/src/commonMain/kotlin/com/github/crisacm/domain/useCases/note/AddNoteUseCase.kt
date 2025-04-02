package com.github.crisacm.domain.useCases.note

import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.repository.NoteRepository

class AddNoteUseCase(
  private val noteRepository: NoteRepository,
) {
  suspend operator fun invoke(note: Note): Long = noteRepository.addNote(note)
}
