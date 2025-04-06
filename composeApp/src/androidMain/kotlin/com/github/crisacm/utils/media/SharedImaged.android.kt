package com.github.crisacm.utils.media

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.io.IOException
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

actual class SharedImage(
  private val context: Context,
  private val bitmap: Bitmap?,
) {
  actual val imagePath: String? = saveImageToTemporaryDirectory(bitmap)

  actual fun toByteArray(): ByteArray? =
    if (bitmap != null) {
      val byteArrayOutputStream = ByteArrayOutputStream()
      @Suppress("MagicNumber")
      bitmap.compress(
        Bitmap.CompressFormat.PNG,
        100,
        byteArrayOutputStream,
      )
      byteArrayOutputStream.toByteArray()
    } else {
      println("toByteArray null")
      null
    }

  actual fun toImageBitmap(): ImageBitmap? {
    val byteArray = toByteArray()
    return if (byteArray != null) {
      return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
    } else {
      println("toImageBitmap null")
      null
    }
  }

  private fun saveImageToTemporaryDirectory(bitmap: Bitmap?): String? {
    val tempDirectory = context.cacheDir // O usa otro directorio temporal adecuado
    val fileName = "temp_image_${System.currentTimeMillis()}.png"
    val file = File(tempDirectory, fileName)
    return try {
      val outputStream = FileOutputStream(file)
      bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
      outputStream.flush()
      outputStream.close()
      file.absolutePath
    } catch (e: IOException) {
      e.printStackTrace()
      null
    }
  }
}
