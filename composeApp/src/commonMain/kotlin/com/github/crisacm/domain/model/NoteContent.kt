package com.github.crisacm.domain.model

import com.github.crisacm.domain.utils.NoteContentType

data class NoteContent(
  var id: Long?,
  var noteId: Long,
  var content: String,
  var checked: Boolean,
  var type: NoteContentType,
  var createdAt: Long,
  var updatedAt: Long,
)
