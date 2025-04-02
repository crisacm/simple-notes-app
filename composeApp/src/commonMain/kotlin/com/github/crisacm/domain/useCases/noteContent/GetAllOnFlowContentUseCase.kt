package com.github.crisacm.domain.useCases.noteContent

import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.repository.NoteContentRepository
import kotlinx.coroutines.flow.Flow

class GetAllOnFlowContentUseCase(
  private val noteContentRepository: NoteContentRepository,
) {
  operator fun invoke(noteId: Long): Flow<List<NoteContent>> = noteContentRepository.getOnFlowNoteContents(noteId)
}
