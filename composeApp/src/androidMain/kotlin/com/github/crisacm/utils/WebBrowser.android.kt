package com.github.crisacm.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

actual class WebBrowser(
  private val context: Context,
) {
  actual fun openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(intent)
  }
}
