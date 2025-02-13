package com.github.crisacm.domain.model

import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_check_box
import just_notes_kmp.composeapp.generated.resources.ic_format_align_center
import just_notes_kmp.composeapp.generated.resources.ic_format_align_left
import just_notes_kmp.composeapp.generated.resources.ic_format_align_right
import just_notes_kmp.composeapp.generated.resources.ic_format_ink_highlighter
import just_notes_kmp.composeapp.generated.resources.ic_format_underlined
import just_notes_kmp.composeapp.generated.resources.ic_imagesmode
import just_notes_kmp.composeapp.generated.resources.ic_mic_google
import just_notes_kmp.composeapp.generated.resources.ic_palette
import just_notes_kmp.composeapp.generated.resources.ic_text_fields
import org.jetbrains.compose.resources.DrawableResource

data class EditorOption(
  val desc: String,
  val icon: DrawableResource,
  val type: EditorOptionType,
)

enum class EditorOptionType {
  FONT_FAMILY,
  MICROPHONE,
  IMAGE,
  CHECKBOX,
  UNDERLINE,
  HIGH_LIGHT,
  COLOR,
  FONT_LEFT,
  FONT_CENTER,
  FONT_RIGHT,
}

val defaultEditorOptions =
  listOf(
    EditorOption(
      desc = "Font Family",
      icon = Res.drawable.ic_text_fields,
      type = EditorOptionType.FONT_FAMILY,
    ),
    EditorOption(
      desc = "Microphone",
      icon = Res.drawable.ic_mic_google,
      type = EditorOptionType.MICROPHONE,
    ),
    EditorOption(
      desc = "Image",
      icon = Res.drawable.ic_imagesmode,
      type = EditorOptionType.IMAGE,
    ),
    EditorOption(
      desc = "Checkbox",
      icon = Res.drawable.ic_check_box,
      type = EditorOptionType.CHECKBOX,
    ),
    EditorOption(
      desc = "Underline",
      icon = Res.drawable.ic_format_underlined,
      type = EditorOptionType.UNDERLINE,
    ),
    EditorOption(
      desc = "Highlight",
      icon = Res.drawable.ic_format_ink_highlighter,
      type = EditorOptionType.HIGH_LIGHT,
    ),
    EditorOption(
      desc = "Color",
      icon = Res.drawable.ic_palette,
      type = EditorOptionType.COLOR,
    ),
    EditorOption(
      desc = "Font Left",
      icon = Res.drawable.ic_format_align_left,
      type = EditorOptionType.FONT_LEFT,
    ),
    EditorOption(
      desc = "Font Center",
      icon = Res.drawable.ic_format_align_center,
      type = EditorOptionType.FONT_CENTER,
    ),
    EditorOption(
      desc = "Font Right",
      icon = Res.drawable.ic_format_align_right,
      type = EditorOptionType.FONT_RIGHT,
    ),
  )
