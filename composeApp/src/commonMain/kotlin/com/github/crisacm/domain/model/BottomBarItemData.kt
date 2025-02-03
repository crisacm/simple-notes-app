package com.github.crisacm.domain.model

import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_picture
import org.jetbrains.compose.resources.DrawableResource

data class BottomBarItemData(
  val drawableId: DrawableResource = Res.drawable.ic_picture,
  val selected: Boolean = false,
  val onClick: () -> Unit = {},
)
