package com.github.crisacm.domain.useCases.noteContent

import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.repository.NoteContentRepository

class GetAllByContentUseCase(
  private val noteContentRepository: NoteContentRepository,
) {
  suspend operator fun invoke(id: Long): List<NoteContent> = noteContentRepository.getNoteContents(id)
}
