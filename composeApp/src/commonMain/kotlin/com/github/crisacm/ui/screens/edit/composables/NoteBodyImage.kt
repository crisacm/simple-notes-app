package com.github.crisacm.ui.screens.edit.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.github.crisacm.ui.theme.NoteYellowDark
import com.github.crisacm.utils.AppLogger
import com.github.crisacm.utils.media.rememberCameraManager
import com.github.crisacm.utils.media.rememberGalleryManager
import com.github.crisacm.utils.permissions.PermissionCallback
import com.github.crisacm.utils.permissions.PermissionStatus
import com.github.crisacm.utils.permissions.PermissionType
import com.github.crisacm.utils.permissions.createPermissionsManager
import just_notes_kmp.composeapp.generated.resources.Res
import just_notes_kmp.composeapp.generated.resources.ic_add_photo
import just_notes_kmp.composeapp.generated.resources.ic_imagesmode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource

@Suppress("MagicNumber", "LongMethod")
@Composable
fun BodyTypeImage(
  modifier: Modifier,
  value: String,
  onValueChange: (String) -> Unit,
) {
  val coroutineScope = rememberCoroutineScope()
  var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
  var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
  var launchCamera by remember { mutableStateOf(value = false) }
  var launchGallery by remember { mutableStateOf(value = false) }
  var launchSetting by remember { mutableStateOf(value = false) }
  var permissionRationalDialog by remember { mutableStateOf(value = false) }
  val permissionsManager =
    createPermissionsManager(
      object : PermissionCallback {
        override fun onPermissionStatus(
          permissionType: PermissionType,
          status: PermissionStatus,
        ) {
          when (status) {
            PermissionStatus.GRANTED -> {
              when (permissionType) {
                PermissionType.CAMERA -> launchCamera = true
                PermissionType.GALLERY -> launchGallery = true
              }
            }

            else -> {
              permissionRationalDialog = true
            }
          }
        }
      },
    )

  val cameraManager =
    rememberCameraManager {
      coroutineScope.launch {
        val bitmap =
          withContext(Dispatchers.Default) {
            it?.toImageBitmap()
          }
        imageBitmap = bitmap
        it?.imagePath?.let(onValueChange)
        AppLogger.d("NoteBodyImage", "Camera bitmap: $bitmap")
        AppLogger.d("NoteBodyImage", "Camera image path: ${it?.imagePath}")
      }
    }

  val galleryManager =
    rememberGalleryManager {
      coroutineScope.launch {
        val bitmap =
          withContext(Dispatchers.Default) {
            it?.toImageBitmap()
          }
        imageBitmap = bitmap
        it?.imagePath?.let(onValueChange)
        AppLogger.d("NoteBodyImage", "Gallery bitmap: $bitmap")
        AppLogger.d("NoteBodyImage", "Gallery image path: ${it?.imagePath}")
      }
    }

  if (imageSourceOptionDialog) {
    ImageSourceOptionDialog(onDismissRequest = {
      imageSourceOptionDialog = false
    }, onGalleryRequest = {
      imageSourceOptionDialog = false
      launchGallery = true
    }, onCameraRequest = {
      imageSourceOptionDialog = false
      launchCamera = true
    })
  }

  if (launchGallery) {
    if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
      galleryManager.launch()
    } else {
      permissionsManager.askPermission(PermissionType.GALLERY)
    }
    launchGallery = false
  }

  if (launchCamera) {
    if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
      cameraManager.launch()
    } else {
      permissionsManager.askPermission(PermissionType.CAMERA)
    }
    launchCamera = false
  }

  if (launchSetting) {
    permissionsManager.launchSettings()
    launchSetting = false
  }

  if (permissionRationalDialog) {
    AlertMessageDialog(
      title = "Permission Required",
      message =
        "To set your profile picture, please grant this permission. " +
          "You can manage permissions in your device settings.",
      positiveButtonText = "Settings",
      negativeButtonText = "Cancel",
      onPositiveClick = {
        permissionRationalDialog = false
        launchSetting = true
      },
      onNegativeClick = {
        permissionRationalDialog = false
      },
    )
  }

  if (value.isEmpty()) {
    Box(modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
      Button(
        onClick = {
          imageSourceOptionDialog = true
        },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        colors =
          ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
          ),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Icon(
            painterResource(Res.drawable.ic_add_photo),
            contentDescription = "add image icon",
            modifier =
              Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .size(24.dp),
            tint = Color.LightGray.copy(alpha = 0.5f),
          )
          Text(
            text = "Click to add an image",
            color = Color.LightGray.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
          )
        }
      }
    }
  } else {
    Card(
      modifier =
        modifier
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 8.dp)
          .height(180.dp),
      colors = CardDefaults.cardColors().copy(containerColor = NoteYellowDark),
      onClick = { imageSourceOptionDialog = true },
    ) {
      setSingletonImageLoaderFactory { context ->
        ImageLoader
          .Builder(context)
          .crossfade(true)
          .logger(DebugLogger())
          .build()
      }

      Image(
        rememberAsyncImagePainter(value),
        contentDescription = "Note image",
        contentScale = ContentScale.Crop,
        modifier =
          Modifier
            .fillMaxWidth()
            .aspectRatio(2 / 3f)
            .clip(MaterialTheme.shapes.medium),
      )
    }
  }
}

@Composable
fun ImageSourceOptionDialog(
  onDismissRequest: () -> Unit,
  onGalleryRequest: () -> Unit = {},
  onCameraRequest: () -> Unit = {},
) {
  Dialog(
    onDismissRequest = onDismissRequest,
    properties =
      DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
      ),
  ) {
    Card(shape = RoundedCornerShape(20.dp)) {
      Column(
        modifier =
          Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = "Select an Image Source",
          color = MaterialTheme.colorScheme.onSurface,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
          textAlign = TextAlign.Center,
        )
        Row {
          Card(
            modifier =
              Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { onCameraRequest.invoke() },
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
          ) {
            Column(
              modifier =
                Modifier
                  .padding(vertical = 24.dp)
                  .fillMaxWidth(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally,
            ) {
              Icon(
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(25.dp),
                painter = painterResource(Res.drawable.ic_add_photo),
                contentDescription = null,
              )
              Text(text = "Camera", color = MaterialTheme.colorScheme.onSurface)
            }
          }
          Text(
            text = "or",
            modifier = Modifier.align(Alignment.CenterVertically),
          )
          Card(
            modifier =
              Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = { onGalleryRequest.invoke() },
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
          ) {
            Column(
              modifier =
                Modifier
                  .padding(vertical = 24.dp)
                  .fillMaxWidth(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally,
            ) {
              Icon(
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(25.dp),
                painter = painterResource(Res.drawable.ic_imagesmode),
                contentDescription = null,
              )
              Text(text = "Gallery", color = MaterialTheme.colorScheme.onSurface)
            }
          }
        }
      }
    }
  }
}

@Composable
fun AlertMessageDialog(
  title: String,
  message: String? = null,
  positiveButtonText: String? = null,
  negativeButtonText: String? = null,
  onPositiveClick: () -> Unit = {},
  onNegativeClick: () -> Unit = {},
) {
  Dialog(
    onDismissRequest = {},
    properties =
      DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
      ),
  ) {
    Surface(
      modifier = Modifier.fillMaxWidth().wrapContentHeight(),
      shape = RoundedCornerShape(size = 12.dp),
    ) {
      Column(
        modifier =
          Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Image(
          Icons.Default.Clear,
          modifier = Modifier.size(100.dp),
          contentDescription = null,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
          text = title,
          fontSize = MaterialTheme.typography.titleSmall.fontSize,
          fontWeight = FontWeight.Medium,
          color = MaterialTheme.colorScheme.onBackground,
          textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(10.dp))
        message?.let {
          Text(
            text = it,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
          )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.fillMaxWidth().padding(end = 16.dp, start = 16.dp),
        ) {
          negativeButtonText?.let {
            Button(
              modifier = Modifier.weight(1f),
              onClick = {
                onNegativeClick()
              },
              colors =
                ButtonDefaults.buttonColors(
                  contentColor = Color.White,
                  containerColor = MaterialTheme.colorScheme.primary,
                ),
            ) {
              Text(text = it, textAlign = TextAlign.Center, maxLines = 1)
            }

            Spacer(modifier = Modifier.width(6.dp))
          }
          positiveButtonText?.let {
            Button(
              modifier = Modifier.weight(1f),
              onClick = {
                onPositiveClick()
              },
              colors =
                ButtonDefaults.buttonColors(
                  contentColor = Color.White,
                  containerColor = MaterialTheme.colorScheme.primary,
                ),
            ) {
              Text(text = it, textAlign = TextAlign.Center, maxLines = 1)
            }
          }
        }
      }
    }
  }
}
