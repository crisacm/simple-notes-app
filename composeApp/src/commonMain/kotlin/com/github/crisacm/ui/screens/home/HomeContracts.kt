package com.github.crisacm.ui.screens.home

import com.github.crisacm.domain.model.Note
import com.github.crisacm.ui.base.ViewEvent
import com.github.crisacm.ui.base.ViewSideEffect
import com.github.crisacm.ui.base.ViewState

class HomeContracts {
  sealed interface Event : ViewEvent {
    data object GoToGithub : Event

    data class Search(
      val query: String,
    ) : Event

    data class Edit(
      val id: String,
    ) : Event
  }

  data class State(
    val idle: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null,
    val notes: List<Note> = emptyList(),
  ) : ViewState

  sealed interface Effect : ViewSideEffect {
    sealed interface Navigation : Effect {
      data object ToGitHub : Navigation

      data class ToEdit(
        val id: String,
      ) : Navigation
    }
  }
}
