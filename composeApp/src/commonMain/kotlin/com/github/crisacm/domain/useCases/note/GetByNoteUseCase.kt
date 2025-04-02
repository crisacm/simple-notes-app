package com.github.crisacm.domain.useCases.note

import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.repository.NoteRepository

class GetByNoteUseCase(
  private val noteRepository: NoteRepository,
) {
  suspend operator fun invoke(id: Long): Note? = noteRepository.getBy(id)
}
