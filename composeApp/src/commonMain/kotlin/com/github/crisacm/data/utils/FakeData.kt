package com.github.crisacm.data.utils

import com.github.crisacm.domain.model.Note
import com.github.crisacm.domain.model.Tag
import com.github.crisacm.domain.utils.NoteColors

val fakeTagsList: List<Tag> =
  listOf(
    Tag(
      id = 1,
      name = "All",
      createdAt = 0,
      updatedAt = 0,
    ),
    Tag(
      id = 2,
      name = "Work",
      createdAt = 0,
      updatedAt = 0,
    ),
    Tag(
      id = 3,
      name = "Reading",
      createdAt = 0,
      updatedAt = 0,
    ),
    Tag(
      id = 4,
      name = "Important",
      createdAt = 0,
      updatedAt = 0,
    ),
  )

val fakeNotesList: List<Note> =
  listOf(
    Note(
      id = 1,
      title = "Purpose of life",
      /*
      content =
        listOf(
          NoteContent(
            id = 1,
            noteId = 1,
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            checked = false,
            type = NoteContentType.TEXT,
            createdAt = 0,
            updatedAt = 0,
          ),
        ),
       */
      color = NoteColors.White,
      tagId = null,
      createdAt = 0,
      updatedAt = 0,
    ),
    Note(
      id = 2,
      title = "Plan for the today",
      /*
      content =
        listOf(
          NoteContent(
            id = 2,
            noteId = 2,
            content = "Work",
            checked = true,
            type = NoteContentType.CHECKLIST,
            createdAt = 0,
            updatedAt = 0,
          ),
          NoteContent(
            id = 3,
            noteId = 2,
            content = "Meeting",
            checked = false,
            type = NoteContentType.CHECKLIST,
            createdAt = 0,
            updatedAt = 0,
          ),
        ),
       */
      color = NoteColors.Yellow,
      createdAt = 0,
      tagId = null,
      updatedAt = 0,
    ),
    Note(
      id = 3,
      title = "Book covers",
      /*
      content =
        listOf(
          NoteContent(
            id = 4,
            noteId = 3,
            content = "https://cdn-v1.udocz-assets.com/uploads/book/cover/1282527/1282527.jpg",
            checked = true,
            type = NoteContentType.IMAGE,
            createdAt = 0,
            updatedAt = 0,
          ),
          NoteContent(
            id = 3,
            noteId = 5,
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            checked = false,
            type = NoteContentType.TEXT,
            createdAt = 0,
            updatedAt = 0,
          ),
        ),
       */
      color = NoteColors.Orange,
      createdAt = 0,
      tagId = null,
      updatedAt = 0,
    ),
    Note(
      id = 4,
      title = "Buy honey 100% natural",
      /*
      content =
        listOf(
          NoteContent(
            id = 4,
            noteId = 6,
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            checked = false,
            type = NoteContentType.TEXT,
            createdAt = 0,
            updatedAt = 0,
          ),
        ),
       */
      color = NoteColors.Purple,
      createdAt = 0,
      tagId = null,
      updatedAt = 0,
    ),
    Note(
      id = 5,
      title = "Content plan for some activity",
      /*
      content =
        listOf(
          NoteContent(
            id = 5,
            noteId = 7,
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            checked = false,
            type = NoteContentType.TEXT,
            createdAt = 0,
            updatedAt = 0,
          ),
        ),
       */
      color = NoteColors.Green,
      createdAt = 0,
      tagId = null,
      updatedAt = 0,
    ),
    Note(
      id = 6,
      title = "Password gelato cafe near the station",
      /*
      content =
        listOf(
          NoteContent(
            id = 6,
            noteId = 8,
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            checked = false,
            type = NoteContentType.TEXT,
            createdAt = 0,
            updatedAt = 0,
          ),
        ),
       */
      color = NoteColors.Blue,
      createdAt = 0,
      tagId = null,
      updatedAt = 0,
    ),
  )
