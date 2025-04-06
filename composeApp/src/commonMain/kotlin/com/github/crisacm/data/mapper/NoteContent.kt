package com.github.crisacm.data.mapper

import com.github.crisacm.data.database.entities.NoteContentEntity
import com.github.crisacm.domain.model.NoteContent

fun NoteContentEntity.asDomain(): NoteContent =
  NoteContent(
    id = id,
    noteId = noteId,
    content = content,
    checked = checked,
    type = type,
    createdAt = createdAt,
    updatedAt = updatedAt,
  )

fun NoteContent.asEntity(): NoteContentEntity =
  NoteContentEntity(
    id = id,
    noteId = noteId,
    content = content,
    checked = checked,
    type = type,
    createdAt = createdAt,
    updatedAt = updatedAt,
  )
