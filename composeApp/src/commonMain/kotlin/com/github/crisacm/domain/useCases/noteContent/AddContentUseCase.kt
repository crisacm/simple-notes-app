package com.github.crisacm.domain.useCases.noteContent

import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.repository.NoteContentRepository

class AddContentUseCase(
  private val noteContentRepository: NoteContentRepository,
) {
  suspend operator fun invoke(noteContent: NoteContent) {
    noteContentRepository.addNoteContent(noteContent)
  }
}
