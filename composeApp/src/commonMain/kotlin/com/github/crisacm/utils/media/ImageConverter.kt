package com.github.crisacm.utils.media

import androidx.compose.ui.graphics.ImageBitmap

expect class ImageConverter {
  fun convertPathToBitmap(imagePath: String): ImageBitmap?
}
