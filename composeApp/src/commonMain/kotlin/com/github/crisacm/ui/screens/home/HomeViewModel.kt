package com.github.crisacm.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.useCases.note.AddNoteUseCase
import com.github.crisacm.domain.useCases.note.DeleteNoteUseCase
import com.github.crisacm.domain.useCases.note.GetAllNotesUseCase
import com.github.crisacm.domain.useCases.noteContent.AddContentUseCase
import com.github.crisacm.domain.useCases.noteContent.GetAllByContentUseCase
import com.github.crisacm.domain.utils.NoteColors
import com.github.crisacm.domain.utils.NoteContentType
import com.github.crisacm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class HomeViewModel(
  private val addNoteUseCase: AddNoteUseCase,
  private val deleteNoteUseCase: DeleteNoteUseCase,
  private val getAllNotesUseCase: GetAllNotesUseCase,
  private val addContentUseCase: AddContentUseCase,
  private val getAllByContentUseCase: GetAllByContentUseCase,
) : BaseViewModel<HomeContracts.Event, HomeContracts.State, HomeContracts.Effect>() {
  override fun setInitialState(): HomeContracts.State = HomeContracts.State()

  override fun handleEvent(event: HomeContracts.Event) {
    when (event) {
      HomeContracts.Event.LoadNotes -> {
        loadNotes()
      }

      HomeContracts.Event.GoToGithub -> {
        setEffect { HomeContracts.Effect.Navigation.ToGitHub }
      }

      is HomeContracts.Event.Search -> {
        //
      }

      is HomeContracts.Event.Edit -> {
        setEffect { HomeContracts.Effect.Navigation.ToEdit(event.id) }
      }

      is HomeContracts.Event.CreateNote -> {
        addNote(event.firstNoteContentType)
      }

      is HomeContracts.Event.Delete -> {
        deleteNote(event.note)
      }
    }
  }

  private fun loadNotes() {
    viewModelScope.launch(Dispatchers.IO) {
      getAllNotesUseCase().collectLatest {
        it
          .map { note ->
            val contents = getAllByContentUseCase(note.id!!)
            Pair(note, contents)
          }.also { setState { copy(notes = it) } }
      }
    }
  }

  private fun addNote(noteContentType: NoteContentType) {
    viewModelScope.launch(Dispatchers.IO) {
      val note =
        Note(
          id = null,
          tagId = null,
          title = "",
          color = NoteColors.White,
          createdAt = Clock.System.now().toEpochMilliseconds(),
          updatedAt = Clock.System.now().toEpochMilliseconds(),
        )

      val noteId = addNoteUseCase(note)

      noteId.let {
        if (it > 0) {
          addContentUseCase(
            NoteContent(
              id = null,
              noteId = it,
              content = "",
              checked = false,
              type = noteContentType,
              createdAt = Clock.System.now().toEpochMilliseconds(),
              updatedAt = Clock.System.now().toEpochMilliseconds(),
            ),
          )
          setEffect { HomeContracts.Effect.Navigation.ToEdit(it) }
        } else {
          setEffect { HomeContracts.Effect.ShowError("Can't create the note") }
        }
      }
    }
  }

  private fun deleteNote(note: Note) {
    viewModelScope.launch(Dispatchers.IO) {
      deleteNoteUseCase(note)
    }
  }
}
