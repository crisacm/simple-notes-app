package com.github.crisacm.utils.media

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

actual class ImageConverter {
  actual fun convertPathToBitmap(imagePath: String): ImageBitmap? {
    val bitmap = BitmapFactory.decodeFile(File(imagePath).absolutePath)
    return bitmap?.asImageBitmap()
  }
}
