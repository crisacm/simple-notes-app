package com.github.crisacm.utils.permissions

import androidx.compose.runtime.Composable

/**
 * Expects a platform-specific implementation of the PermissionsManager class.
 * This class handles permission requests and status checks.
 *
 * @param callback The callback to handle permission status updates.
 */
expect class PermissionsManager(callback: PermissionCallback) : PermissionHandler

/**
 * Interface for handling permission status updates.
 */
interface PermissionCallback {
  /**
   * Called when the permission status changes.
   *
   * @param permissionType The type of permission.
   * @param status The new status of the permission.
   */
  fun onPermissionStatus(permissionType: PermissionType, status: PermissionStatus)
}

/**
 * Expects a platform-specific implementation of the createPermissionsManager function.
 * This function creates a PermissionsManager instance.
 *
 * @param callback The callback to handle permission status updates.
 * @return A PermissionsManager instance.
 */
@Composable
expect fun createPermissionsManager(callback: PermissionCallback): PermissionsManager

/**
 * Interface for handling permission requests and status checks.
 */
interface PermissionHandler {
  /**
   * Requests a specific permission.
   *
   * @param permission The type of permission to request.
   */
  @Composable
  fun askPermission(permission: PermissionType)

  /**
   * Checks if a specific permission is granted.
   *
   * @param permission The type of permission to check.
   * @return True if the permission is granted, false otherwise.
   */
  @Composable
  fun isPermissionGranted(permission: PermissionType): Boolean

  /**
   * Launches the device settings to allow the user to manage permissions.
   */
  @Composable
  fun launchSettings()
}

/**
 * Enum class representing the types of permissions.
 */
enum class PermissionType {
  CAMERA,
  GALLERY,
}

/**
 * Enum class representing the status of permissions.
 */
enum class PermissionStatus {
  GRANTED,
  DENIED,
  SHOW_RATIONAL,
}
