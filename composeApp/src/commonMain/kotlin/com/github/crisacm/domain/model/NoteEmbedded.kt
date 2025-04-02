package com.github.crisacm.domain.model

data class NoteEmbedded(
  val note: Note,
  val tag: Tag?,
  val content: List<NoteContent>,
)
