package com.github.crisacm.di

import androidx.room.RoomDatabase
import com.github.crisacm.data.database.AppDatabase
import com.github.crisacm.data.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module =
  module {
    single<RoomDatabase.Builder<AppDatabase>> {
      getDatabaseBuilder(get())
    }
  }
