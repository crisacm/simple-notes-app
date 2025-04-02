package com.github.crisacm.utils.media

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage {
  val imagePath: String?

  fun toByteArray(): ByteArray?

  fun toImageBitmap(): ImageBitmap?
}
