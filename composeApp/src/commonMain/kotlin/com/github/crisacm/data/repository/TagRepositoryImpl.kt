package com.github.crisacm.data.repository

import com.github.crisacm.data.database.daos.TagDao
import com.github.crisacm.data.mapper.asDomain
import com.github.crisacm.data.mapper.asEntity
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TagRepositoryImpl(
  private val tagDao: TagDao,
) : TagRepository {
  override fun getAllFlow(): Flow<List<Tag>> =
    tagDao.getAllFlow().map { list ->
      list.map { it.asDomain() }
    }

  override suspend fun getBy(name: String): Tag? = tagDao.getByName(name)?.asDomain()

  override suspend fun getById(id: Long): Tag? = tagDao.getById(id)?.asDomain()

  override suspend fun getAll(): List<Tag> = tagDao.getAll().map { it.asDomain() }

  override suspend fun add(tag: Tag): Long = tagDao.insert(tag.asEntity())

  override suspend fun delete(tag: Tag) {
    tagDao.delete(tag.asEntity())
  }
}
