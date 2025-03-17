package com.github.crisacm.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object Login

@Serializable
object Home

@Serializable
data class EditNote(
  val noteId: String,
)
