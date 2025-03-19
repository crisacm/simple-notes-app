package com.github.crisacm.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class WebBrowser {
  actual fun openUrl(url: String) {
    val nsUrl = NSURL(string = url)
    UIApplication.sharedApplication.openURL(nsUrl)
  }
}
