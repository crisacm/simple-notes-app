package com.github.crisacm.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.crisacm.data.database.entities.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
  @Query("SELECT * FROM tags ORDER BY id DESC")
  suspend fun getAll(): List<TagEntity>

  @Query("SELECT * FROM tags WHERE name = :name LIMIT 1")
  suspend fun getByName(name: String): TagEntity?

  @Query("SELECT * FROM tags WHERE id = :id LIMIT 1")
  suspend fun getById(id: Long): TagEntity?

  @Query("SELECT * FROM tags ORDER BY id DESC")
  fun getAllFlow(): Flow<List<TagEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(tag: TagEntity): Long

  @Delete
  suspend fun delete(tag: TagEntity)
}
