package com.github.crisacm.domain.model

import com.github.crisacm.domain.utils.NoteColors

data class Note(
  var id: Long?,
  var tagId: Long?,
  var title: String,
  var color: NoteColors,
  var createdAt: Long,
  var updatedAt: Long,
)
