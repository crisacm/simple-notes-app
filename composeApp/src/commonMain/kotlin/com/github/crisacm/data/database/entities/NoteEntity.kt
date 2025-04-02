package com.github.crisacm.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.github.crisacm.domain.utils.NoteColors

@Entity(
  tableName = "notes",
  foreignKeys = [
    ForeignKey(
      entity = TagEntity::class,
      parentColumns = ["id"],
      childColumns = ["tagId"],
      onDelete = ForeignKey.SET_NULL,
    ),
  ],
)
data class NoteEntity(
  @PrimaryKey(autoGenerate = true) var id: Long? = null,
  var tagId: Long? = null,
  var title: String,
  var color: NoteColors = NoteColors.White,
  var createdAt: Long,
  var updatedAt: Long,
)
