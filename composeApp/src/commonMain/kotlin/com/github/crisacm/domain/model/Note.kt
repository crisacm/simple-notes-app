package com.github.crisacm.domain.model

import androidx.compose.ui.graphics.Color
import com.github.crisacm.ui.theme.NoteWhite

data class Note(
  var id: String = "",
  var title: String = "",
  var content: List<NoteContent> = emptyList(),
  var color: Color = NoteWhite,
  var date: Long = 0,
  var tags: List<String> = emptyList(),
)

data class NoteContent(
  var id: String = "",
  var noteId: String = "",
  var content: Any = Any(),
  var checked: Boolean = false,
  var type: NoteContentType = NoteContentType.TEXT,
)

enum class NoteContentType {
  TEXT,
  IMAGE,
  AUDIO,
  CHECKLIST,
}
