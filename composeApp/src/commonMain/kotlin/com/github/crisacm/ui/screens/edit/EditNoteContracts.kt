package com.github.crisacm.ui.screens.edit

import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.utils.NoteContentType
import com.github.crisacm.ui.base.ViewEvent
import com.github.crisacm.ui.base.ViewSideEffect
import com.github.crisacm.ui.base.ViewState

class EditNoteContracts {
  sealed interface Event : ViewEvent {
    data object GoBack : Event

    // Tags
    data object RemoveTag : Event

    data class CreateTag(
      val tagName: String,
    ) : Event

    data class UpdateTag(
      val tag: Tag,
    ) : Event

    data class DeleteTag(
      val tag: Tag,
    ) : Event

    // Note Content
    data class AddContent(
      val contentType: NoteContentType,
    ) : Event

    data class UpdateContent(
      val content: NoteContent,
    ) : Event

    data class RemoveContent(
      val content: NoteContent,
    ) : Event

    data class DeleteContent(
      val content: NoteContent,
    ) : Event

    // Note
    data class LoadNoteInfo(
      val id: Long,
    ) : Event

    data class UpdateNote(
      val note: Note.() -> Note,
    ) : Event

    data class DeleteNote(
      val note: Note,
    ) : Event
  }

  data class State(
    val isLoading: Boolean = true,
    val error: String? = null,
    val note: Note? = null,
    val noteTag: Tag? = null,
    val noteContent: List<NoteContent> = emptyList(),
    val tags: List<Tag> = emptyList(),
  ) : ViewState

  sealed interface Effect : ViewSideEffect {
    data class ShowError(
      val message: String,
    ) : Effect

    sealed interface Navigation : Effect {
      data object ToHome : Navigation
    }
  }
}
