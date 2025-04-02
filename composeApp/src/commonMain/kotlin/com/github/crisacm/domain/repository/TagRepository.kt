package com.github.crisacm.domain.repository

import com.github.crisacm.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
  fun getAllFlow(): Flow<List<Tag>>

  suspend fun getBy(name: String): Tag?

  suspend fun getById(id: Long): Tag?

  suspend fun getAll(): List<Tag>

  suspend fun add(tag: Tag): Long

  suspend fun delete(tag: Tag)
}
