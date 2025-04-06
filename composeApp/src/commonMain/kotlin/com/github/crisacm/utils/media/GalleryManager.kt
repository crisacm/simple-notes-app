package com.github.crisacm.utils.media

import androidx.compose.runtime.Composable
import com.github.crisacm.utils.media.SharedImage

@Composable
expect fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager

expect class GalleryManager(
  onLaunch: () -> Unit,
) {
  fun launch()
}
