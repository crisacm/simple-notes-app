package com.github.crisacm.data.mapper

import com.github.crisacm.data.database.entities.NoteEmbeddedEntity
import com.github.crisacm.data.database.entities.NoteEntity
import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.NoteEmbedded

fun Note.asEntity(): NoteEntity =
  NoteEntity(
    id = id,
    tagId = tagId,
    title = title,
    color = color,
    createdAt = createdAt,
    updatedAt = updatedAt,
  )

fun NoteEntity.asDomain(): Note =
  Note(
    id = id,
    tagId = tagId,
    title = title,
    color = color,
    createdAt = createdAt,
    updatedAt = updatedAt,
  )

fun NoteEmbeddedEntity.asDomain(): NoteEmbedded =
  NoteEmbedded(
    note = noteEntity.asDomain(),
    tag = tagEntity?.asDomain(),
    content = contentEntityList.map { it.asDomain() },
  )
