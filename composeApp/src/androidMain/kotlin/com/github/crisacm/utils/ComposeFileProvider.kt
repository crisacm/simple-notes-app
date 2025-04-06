package com.github.crisacm.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.github.crisacm.R
import java.io.File

class ComposeFileProvider :
  FileProvider(
    R.xml.provider_paths,
  ) {
  companion object {
    fun getImageUri(context: Context): Uri {
      val tempFile =
        File
          .createTempFile(
            "picture_${System.currentTimeMillis()}",
            ".png",
            context.cacheDir,
          ).apply {
            createNewFile()
          }

      val authority = context.packageName + ".provider"
      return getUriForFile(
        context,
        authority,
        tempFile,
      )
    }
  }
}
