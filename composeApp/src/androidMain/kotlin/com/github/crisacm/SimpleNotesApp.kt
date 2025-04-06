package com.github.crisacm

import android.app.Application
import com.github.crisacm.di.initKoin
import com.github.crisacm.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.logger.Level

class SimpleNotesApp :
  Application(),
  KoinComponent {
  override fun onCreate() {
    super.onCreate()
    initKoin {
      androidLogger(Level.DEBUG)
      androidContext(this@SimpleNotesApp)
      modules(platformModule())
    }
  }
}
