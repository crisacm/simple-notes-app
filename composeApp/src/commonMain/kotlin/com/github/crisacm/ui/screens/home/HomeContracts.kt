package com.github.crisacm.ui.screens.home

import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.utils.NoteContentType
import com.github.crisacm.ui.base.ViewEvent
import com.github.crisacm.ui.base.ViewSideEffect
import com.github.crisacm.ui.base.ViewState

class HomeContracts {
  sealed interface Event : ViewEvent {
    data object LoadNotes : Event

    data object GoToGithub : Event

    data class Search(
      val query: String,
    ) : Event

    data class Edit(
      val id: Long,
    ) : Event

    data class Delete(
      val note: Note,
    ) : Event

    data class CreateNote(
      val firstNoteContentType: NoteContentType,
    ) : Event
  }

  data class State(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null,
    val notes: List<Pair<Note, List<NoteContent>>> = emptyList(),
    val tags: List<Tag> = emptyList(),
  ) : ViewState

  sealed interface Effect : ViewSideEffect {
    data class ShowError(
      val message: String,
    ) : Effect

    sealed interface Navigation : Effect {
      data object ToGitHub : Navigation

      data class ToEdit(
        val id: Long,
      ) : Navigation
    }
  }
}
