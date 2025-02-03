package com.github.crisacm.domain.model

import com.github.crisacm.ui.theme.NoteBlue
import com.github.crisacm.ui.theme.NoteGreen
import com.github.crisacm.ui.theme.NoteOrange
import com.github.crisacm.ui.theme.NotePurple
import com.github.crisacm.ui.theme.NoteWhite
import com.github.crisacm.ui.theme.NoteYellow

val fakeTagsList =
  listOf(
    Tag(
      id = "1",
      name = "All",
    ),
    Tag(
      id = "2",
      name = "Work",
    ),
    Tag(
      id = "3",
      name = "Reading",
    ),
    Tag(
      id = "4",
      name = "Important",
    ),
  )

val fakeNotesList =
  listOf(
    Note(
      id = "1",
      title = "Purpose of life",
      content =
        listOf(
          NoteContent(
            id = "1",
            noteId = "1",
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            type = NoteContentType.TEXT,
          ),
        ),
      color = NoteWhite,
      date = 0,
      tags = emptyList(),
    ),
    Note(
      id = "2",
      title = "Plan for the today",
      content =
        listOf(
          NoteContent(
            id = "2",
            noteId = "2",
            content = "Work",
            checked = true,
            type = NoteContentType.CHECKLIST,
          ),
          NoteContent(
            id = "3",
            noteId = "2",
            content = "Meeting",
            type = NoteContentType.CHECKLIST,
          ),
        ),
      color = NoteYellow,
      date = 0,
      tags = emptyList(),
    ),
    Note(
      id = "3",
      title = "Book covers",
      content =
        listOf(
          NoteContent(
            id = "4",
            noteId = "3",
            content = "https://cdn-v1.udocz-assets.com/uploads/book/cover/1282527/1282527.jpg",
            checked = true,
            type = NoteContentType.IMAGE,
          ),
          NoteContent(
            id = "3",
            noteId = "5",
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            type = NoteContentType.TEXT,
          ),
        ),
      color = NoteOrange,
      date = 0,
      tags = emptyList(),
    ),
    Note(
      id = "4",
      title = "Buy honey 100% natural",
      content =
        listOf(
          NoteContent(
            id = "4",
            noteId = "6",
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            type = NoteContentType.TEXT,
          ),
        ),
      color = NotePurple,
      date = 0,
      tags = emptyList(),
    ),
    Note(
      id = "5",
      title = "Content plan for some activity",
      content =
        listOf(
          NoteContent(
            id = "5",
            noteId = "7",
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            type = NoteContentType.TEXT,
          ),
        ),
      color = NoteGreen,
      date = 0,
      tags = emptyList(),
    ),
    Note(
      id = "6",
      title = "Password gelato cafe near the station",
      content =
        listOf(
          NoteContent(
            id = "6",
            noteId = "8",
            content =
              "lore ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut " +
                "labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat",
            type = NoteContentType.TEXT,
          ),
        ),
      color = NoteBlue,
      date = 0,
      tags = emptyList(),
    ),
  )
