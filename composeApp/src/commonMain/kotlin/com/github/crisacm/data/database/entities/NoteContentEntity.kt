package com.github.crisacm.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.github.crisacm.domain.utils.NoteContentType

@Entity(
  tableName = "note_content",
  foreignKeys = [
    ForeignKey(
      entity = NoteEntity::class,
      parentColumns = ["id"],
      childColumns = ["noteId"],
      onDelete = ForeignKey.CASCADE,
    ),
  ],
)
data class NoteContentEntity(
  @PrimaryKey(autoGenerate = true) var id: Long? = null,
  var noteId: Long,
  var content: String = "",
  var checked: Boolean = false,
  var type: NoteContentType = NoteContentType.TEXT,
  var createdAt: Long,
  var updatedAt: Long,
)
