package com.github.crisacm.utils.media

import androidx.compose.ui.graphics.ImageBitmap
import platform.Foundation.NSData
import platform.Foundation.dataWithContentsOfFile
import platform.UIKit.UIImage

actual class ImageConverter {
  actual fun convertPathToBitmap(imagePath: String): ImageBitmap? {
    val nsData = NSData.dataWithContentsOfFile(imagePath)
    val uiImage = nsData?.let { UIImage(data = nsData) }
    return null
  }
}
