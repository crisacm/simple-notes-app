package com.github.crisacm.utils.media

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.reinterpret
import kotlinx.datetime.Clock
import org.jetbrains.skia.Image
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.writeToURL
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.darwin.ByteVar

actual class SharedImage(
    private val image: UIImage?,
) {
  actual val imagePath: String? = image?.let { saveImageToTemporaryDirectory(it) }

  @OptIn(ExperimentalForeignApi::class)
  actual fun toByteArray(): ByteArray? =
    if (image != null) {
      val imageData =
        UIImageJPEGRepresentation(image, COMPRESSION_QUALITY)
          ?: throw IllegalArgumentException("image data is null")
      val bytes = imageData.bytes ?: throw IllegalArgumentException("image bytes is null")
      val length = imageData.length

      val data: CPointer<ByteVar> = bytes.reinterpret()
      ByteArray(length.toInt()) { index -> data[index].toByte() }
    } else {
      null
    }

  actual fun toImageBitmap(): ImageBitmap? {
    val byteArray = toByteArray()
    return if (byteArray != null) {
      Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    } else {
      null
    }
  }

  private companion object {
    const val COMPRESSION_QUALITY = 0.99
  }

  private fun saveImageToTemporaryDirectory(image: UIImage): String? {
    val imageData = UIImageJPEGRepresentation(image, COMPRESSION_QUALITY)
    val tempDirectory = NSTemporaryDirectory()
    val fileName = "temp_image_${Clock.System.now().toEpochMilliseconds()}.jpg"
    val filePath = tempDirectory + fileName
    val fileUrl = NSURL.fileURLWithPath(filePath)

    return if (imageData?.writeToURL(fileUrl, true) == true) {
      filePath
    } else {
      null
    }
  }
}
