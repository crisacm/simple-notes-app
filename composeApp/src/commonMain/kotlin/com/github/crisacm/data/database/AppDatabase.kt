package com.github.crisacm.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.github.crisacm.data.database.daos.NoteContentDao
import com.github.crisacm.data.database.daos.NoteDao
import com.github.crisacm.data.database.daos.TagDao
import com.github.crisacm.data.database.entities.NoteContentEntity
import com.github.crisacm.data.database.entities.NoteEntity
import com.github.crisacm.data.database.entities.TagEntity
import com.github.crisacm.data.database.utils.Converters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

const val DATABASE_NAME = "simple-notes.db"

@Database(
  entities = [
    NoteEntity::class,
    NoteContentEntity::class,
    TagEntity::class,
  ],
  version = 1,
  exportSchema = false,
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun noteDao(): NoteDao

  abstract fun noteContentDao(): NoteContentDao

  abstract fun tagDao(): TagDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
  override fun initialize(): AppDatabase
}

fun getRoomDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase =
  builder
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
