package com.github.crisacm.utils.media

import androidx.compose.runtime.Composable
import com.github.crisacm.utils.media.SharedImage

@Composable
expect fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager

expect class CameraManager(
  onLaunch: () -> Unit,
) {
  fun launch()
}
