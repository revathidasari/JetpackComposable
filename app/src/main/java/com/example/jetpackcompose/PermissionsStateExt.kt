package com.example.jetpackcompose

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied() : Boolean {
    //when not rationalised/mandatory & not having permission = denied
    return !shouldShowRationale && !hasPermission
}