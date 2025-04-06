package com.github.crisacm.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
  @PrimaryKey(autoGenerate = true) var id: Long? = null,
  var name: String,
  var createdAt: Long,
  var updatedAt: Long,
)
