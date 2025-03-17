package com.github.crisacm.di

import com.github.crisacm.data.database.AppDatabase
import com.github.crisacm.data.database.NoteRepositoryImpl
import com.github.crisacm.data.database.getRoomDatabase
import com.github.crisacm.ui.screens.home.HomeViewModel
import com.github.crisacm.ui.screens.login.LoginViewModel
import com.github.crisacm.ui.screens.splash.SplashViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

val databaseModule =
  module {
    single<AppDatabase> { getRoomDatabase(get()) }
  }

val dataModule =
  module {
    factoryOf(::NoteRepositoryImpl)
  }

val viewModelsModule =
  module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SplashViewModel)
  }

fun initKoin(config: KoinAppDeclaration? = null) {
  startKoin {
    config?.invoke(this)
    modules(platformModule(), databaseModule, dataModule, viewModelsModule)
  }
}
