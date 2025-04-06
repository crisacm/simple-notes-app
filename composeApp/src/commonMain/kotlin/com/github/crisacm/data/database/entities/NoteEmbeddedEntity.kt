package com.github.crisacm.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class NoteEmbeddedEntity(
  @Embedded val noteEntity: NoteEntity,
  @Relation(
    parentColumn = "id",
    entityColumn = "noteId",
  ) val tagEntity: TagEntity? = null,
  @Relation(
    parentColumn = "id",
    entityColumn = "noteId",
  ) val contentEntityList: List<NoteContentEntity> = emptyList(),
)
