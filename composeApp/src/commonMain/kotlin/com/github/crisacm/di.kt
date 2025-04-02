package com.github.crisacm.di

import com.github.crisacm.data.database.AppDatabase
import com.github.crisacm.data.database.daos.NoteContentDao
import com.github.crisacm.data.database.daos.NoteDao
import com.github.crisacm.data.database.daos.TagDao
import com.github.crisacm.data.database.getRoomDatabase
import com.github.crisacm.data.repository.NoteContentRepositoryImpl
import com.github.crisacm.data.repository.NoteRepositoryImpl
import com.github.crisacm.data.repository.TagRepositoryImpl
import com.github.crisacm.domain.repository.NoteContentRepository
import com.github.crisacm.domain.repository.NoteRepository
import com.github.crisacm.domain.repository.TagRepository
import com.github.crisacm.domain.useCases.note.AddNoteUseCase
import com.github.crisacm.domain.useCases.note.DeleteNoteUseCase
import com.github.crisacm.domain.useCases.note.GetAllNotesUseCase
import com.github.crisacm.domain.useCases.note.GetByNoteUseCase
import com.github.crisacm.domain.useCases.note.UpdateNoteUseCase
import com.github.crisacm.domain.useCases.noteContent.AddContentUseCase
import com.github.crisacm.domain.useCases.noteContent.UpdateContentUseCase
import com.github.crisacm.domain.useCases.noteContent.DeleteContentUseCase
import com.github.crisacm.domain.useCases.noteContent.GetAllOnFlowContentUseCase
import com.github.crisacm.domain.useCases.noteContent.GetAllByContentUseCase
import com.github.crisacm.domain.useCases.tag.AddTagUseCase
import com.github.crisacm.domain.useCases.tag.DeleteTagUseCase
import com.github.crisacm.domain.useCases.tag.GetAllTagsFlowUseCase
import com.github.crisacm.domain.useCases.tag.GetAllTagsUseCase
import com.github.crisacm.domain.useCases.tag.GetByIdTagUseCase
import com.github.crisacm.domain.useCases.tag.GetByTagUseCase
import com.github.crisacm.ui.screens.edit.EditNoteViewModel
import com.github.crisacm.ui.screens.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun platformModule(): Module

val databaseModule =
  module {
    single<AppDatabase> { getRoomDatabase(get()) }
    single<NoteDao> { get<AppDatabase>().noteDao() }
    single<NoteContentDao> { get<AppDatabase>().noteContentDao() }
    single<TagDao> { get<AppDatabase>().tagDao() }
  }

val dataModule =
  module {
    single<NoteRepository> {
      NoteRepositoryImpl(get())
    }.bind(NoteRepository::class)

    single<NoteContentRepository> {
      NoteContentRepositoryImpl(get())
    }.bind(NoteContentRepository::class)

    single<TagRepository> {
      TagRepositoryImpl(get())
    }.bind(TagRepository::class)
  }

val useCasesModule =
  module {
    // Notes
    singleOf(::AddNoteUseCase)
    singleOf(::GetAllNotesUseCase)
    singleOf(::GetByNoteUseCase)
    singleOf(::UpdateNoteUseCase)
    singleOf(::DeleteNoteUseCase)
    // Note Content
    singleOf(::AddContentUseCase)
    singleOf(::UpdateContentUseCase)
    singleOf(::DeleteContentUseCase)
    singleOf(::GetAllOnFlowContentUseCase)
    singleOf(::GetAllByContentUseCase)
    // Tags
    singleOf(::AddTagUseCase)
    singleOf(::DeleteTagUseCase)
    singleOf(::GetAllTagsUseCase)
    singleOf(::GetAllTagsFlowUseCase)
    singleOf(::GetByTagUseCase)
    singleOf(::GetByIdTagUseCase)
  }

val viewModelsModule =
  module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::EditNoteViewModel)
  }

fun initKoin(config: KoinAppDeclaration? = null) {
  startKoin {
    config?.invoke(this)
    modules(
      platformModule(),
      databaseModule,
      dataModule,
      useCasesModule,
      viewModelsModule,
    )
  }
}
