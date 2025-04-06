package com.github.crisacm.data.mapper

import com.github.crisacm.data.database.entities.TagEntity
import com.github.crisacm.domain.model.Tag

fun Tag.asEntity(): TagEntity =
  TagEntity(
    id = id,
    name = name,
    createdAt = createdAt,
    updatedAt = updatedAt,
  )

fun TagEntity.asDomain(): Tag =
  Tag(
    id = id,
    name = name,
    createdAt = createdAt,
    updatedAt = updatedAt,
  )
