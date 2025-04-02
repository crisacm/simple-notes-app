package com.github.crisacm.ui.screens.edit

import androidx.lifecycle.viewModelScope
import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.NoteContent
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.useCases.note.DeleteNoteUseCase
import com.github.crisacm.domain.useCases.note.GetByNoteUseCase
import com.github.crisacm.domain.useCases.note.UpdateNoteUseCase
import com.github.crisacm.domain.useCases.noteContent.AddContentUseCase
import com.github.crisacm.domain.useCases.noteContent.DeleteContentUseCase
import com.github.crisacm.domain.useCases.noteContent.GetAllOnFlowContentUseCase
import com.github.crisacm.domain.useCases.noteContent.UpdateContentUseCase
import com.github.crisacm.domain.useCases.tag.AddTagUseCase
import com.github.crisacm.domain.useCases.tag.DeleteTagUseCase
import com.github.crisacm.domain.useCases.tag.GetAllTagsFlowUseCase
import com.github.crisacm.domain.useCases.tag.GetByIdTagUseCase
import com.github.crisacm.domain.useCases.tag.GetByTagUseCase
import com.github.crisacm.domain.utils.NoteContentType
import com.github.crisacm.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

@Suppress("LongParameterList", "TooManyFunctions")
class EditNoteViewModel(
  private val updateNoteUseCase: UpdateNoteUseCase,
  private val deleteNoteUseCase: DeleteNoteUseCase,
  private val getByNoteUseCase: GetByNoteUseCase,
  private val addTagUseCase: AddTagUseCase,
  private val deleteTagUseCase: DeleteTagUseCase,
  private val getByTagUseCase: GetByTagUseCase,
  private val getByIdTagUseCase: GetByIdTagUseCase,
  private val getAllTagsFlowUseCase: GetAllTagsFlowUseCase,
  private val addContentUseCase: AddContentUseCase,
  private val updateContentUseCase: UpdateContentUseCase,
  private val deleteContentUseCase: DeleteContentUseCase,
  private val getAllOnFlowContentUseCase: GetAllOnFlowContentUseCase,
) : BaseViewModel<EditNoteContracts.Event, EditNoteContracts.State, EditNoteContracts.Effect>() {
  private var cachedData: Note? = null

  override fun setInitialState(): EditNoteContracts.State = EditNoteContracts.State()

  override fun handleEvent(event: EditNoteContracts.Event) {
    when (event) {
      is EditNoteContracts.Event.DeleteNote -> {
        deleteNote()
      }

      EditNoteContracts.Event.GoBack -> {
        setEffect { EditNoteContracts.Effect.Navigation.ToHome }
      }

      is EditNoteContracts.Event.LoadNoteInfo -> {
        loadNoteInfo(event.id)
      }

      is EditNoteContracts.Event.UpdateNote -> {
        viewModelScope.launch {
          cachedData = event.note.invoke(cachedData!!)
          updateNoteUseCase(cachedData!!)
          setState { copy(note = cachedData) }
        }
      }

      is EditNoteContracts.Event.RemoveTag -> {
        removeTag()
      }

      is EditNoteContracts.Event.CreateTag -> {
        createTag(event.tagName)
      }

      is EditNoteContracts.Event.DeleteTag -> {
        deleteTag(event.tag)
      }

      is EditNoteContracts.Event.UpdateTag -> {
        updateTag(event.tag)
      }

      is EditNoteContracts.Event.AddContent -> {
        addContent(event.contentType)
      }

      is EditNoteContracts.Event.UpdateContent -> {
        updateContent(event.content)
      }

      is EditNoteContracts.Event.RemoveContent -> {
        removeContent(event.content)
      }

      is EditNoteContracts.Event.DeleteContent -> {
        deleteContent(event.content)
      }
    }
  }

  private fun updateContent(noteContent: NoteContent) {
    viewModelScope.launch(Dispatchers.IO) {
      updateContentUseCase(noteContent)
    }
  }

  private fun addContent(noteContentType: NoteContentType) {
    viewModelScope.launch(Dispatchers.IO) {
      val noteContent =
        NoteContent(
          id = null,
          noteId = cachedData!!.id!!,
          content = "",
          checked = false,
          type = noteContentType,
          createdAt = Clock.System.now().toEpochMilliseconds(),
          updatedAt = Clock.System.now().toEpochMilliseconds(),
        )

      val lastContent = viewState.value.noteContent.lastOrNull()
      if (lastContent != null) {
        if (noteContentType == lastContent.type && lastContent.content.isEmpty()) {
          setEffect { EditNoteContracts.Effect.ShowError("Ya existe un contenido vacío") }
          return@launch
        }
      }

      addContentUseCase(noteContent)
    }
  }

  private fun removeContent(noteContent: NoteContent) {
    viewModelScope.launch {
      deleteContentUseCase(noteContent)
    }
  }

  private fun deleteContent(noteContent: NoteContent) {
    viewModelScope.launch(Dispatchers.IO) {
      deleteContentUseCase(noteContent)
    }
  }

  private fun removeTag() {
    viewModelScope.launch {
      cachedData = cachedData!!.copy(tagId = null)
      setState { copy(noteTag = null) }
      updateNote()
    }
  }

  private fun createTag(name: String) {
    viewModelScope.launch {
      val tagId =
        addTagUseCase(
          Tag(
            id = null,
            name = name,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            updatedAt = Clock.System.now().toEpochMilliseconds(),
          ),
        )

      val tag = getByTagUseCase(name)
      cachedData = cachedData!!.copy(tagId = tagId)
      setState { copy(noteTag = tag) }
      updateNote()
    }
  }

  private fun deleteTag(tag: Tag) {
    viewModelScope.launch {
      deleteTagUseCase(tag)

      if (cachedData?.tagId == tag.id) {
        cachedData = cachedData!!.copy(tagId = null)
        setState { copy(noteTag = null) }
        updateNote()
      }
    }
  }

  private fun updateTag(tag: Tag) {
    viewModelScope.launch(Dispatchers.IO) {
      cachedData = cachedData!!.copy(tagId = tag.id)
      setState { copy(noteTag = tag) }
      updateNote()
    }
  }

  private fun loadNoteInfo(id: Long) {
    viewModelScope
      .launch(Dispatchers.IO) {
        setState { copy(isLoading = true, error = null) }

        getByNoteUseCase(id).let {
          if (it != null) {
            val noteTag = getByIdTagUseCase(it.tagId ?: 0)
            cachedData = it
            setState { copy(isLoading = false, note = cachedData, noteTag = noteTag) }
          } else {
            setState { copy(isLoading = false, error = "Can't load Note Info") }
          }
        }
      }.invokeOnCompletion {
        viewModelScope.launch(Dispatchers.IO) {
          getAllTagsFlowUseCase().collectLatest {
            setState { copy(tags = it) }
          }
        }

        viewModelScope.launch {
          getAllOnFlowContentUseCase(id).collectLatest {
            setState { copy(noteContent = it) }
          }
        }
      }
  }

  private suspend fun updateNote() {
    updateNoteUseCase(cachedData!!)
    setState { copy(note = cachedData) }
  }

  private fun deleteNote() {
    viewModelScope.launch(Dispatchers.IO) {
      deleteNoteUseCase(cachedData!!)
      setEffect { EditNoteContracts.Effect.Navigation.ToHome }
    }
  }
}
