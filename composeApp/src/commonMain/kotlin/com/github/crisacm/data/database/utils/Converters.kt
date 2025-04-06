package com.github.crisacm.data.database.utils

import androidx.room.TypeConverter
import com.github.crisacm.domain.utils.NoteColors
import com.github.crisacm.domain.utils.NoteContentType

class Converters {
  @TypeConverter
  fun fromNoteContentType(type: NoteContentType): Int = type.ordinal

  @TypeConverter
  fun toNoteContentType(ordinal: Int): NoteContentType = NoteContentType.entries[ordinal]

  @TypeConverter
  fun fromNoteColors(colors: NoteColors): Int = colors.ordinal

  @TypeConverter
  fun toNoteColors(ordinal: Int): NoteColors = NoteColors.entries[ordinal]
}
