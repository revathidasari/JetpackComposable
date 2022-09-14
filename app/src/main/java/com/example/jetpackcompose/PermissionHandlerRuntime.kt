package com.example.jetpackcompose

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@ExperimentalPermissionsApi
@Composable
fun PermissionHandleRuntime() {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO
        )
    )
    val currentlifecycleowner = LocalLifecycleOwner.current
    //to execute till acceptable and dispose after it
    DisposableEffect(key1 = currentlifecycleowner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissionState.launchMultiplePermissionRequest()
                }
            }
            currentlifecycleowner.lifecycle.addObserver(observer)

            onDispose {
                currentlifecycleowner.lifecycle.removeObserver(observer)
            }

        } )

    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        permissionState.permissions.forEach { perm->
            when(perm.permission) {
                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    when {
                        perm.hasPermission -> {
                            Text(text = "Read external storage permission is accepted")
                        }
                        perm.shouldShowRationale -> {
                            Text(text = "Read external storage permission is needed. so please accept")
                        }
                        perm.isPermanentlyDenied() -> {
                            Text(text = "Read external storage permission is denied " +
                                    "please enable from app settings")
                        }
                    }
                }
                Manifest.permission.INTERNET -> {
                    when {
                        perm.hasPermission -> {
                            Text(text = "Internet permission is accepted")
                        }
                        perm.shouldShowRationale -> {
                            Text(text = "Internet permission  is needed. so please accept")
                        }
                        perm.isPermanentlyDenied() -> {
                            Text(text = "Internet permission  is denied " +
                                    "please enable from app settings")
                        }
                    }
                }
                Manifest.permission.RECORD_AUDIO -> {
                    when {
                        perm.hasPermission -> {
                            Text(text = "Record Audio permission is accepted")
                        }
                        perm.shouldShowRationale -> {
                            Text(text = "Record Audio permission  is needed. so please accept")
                        }
                        perm.isPermanentlyDenied() -> {
                            Text(text = "Record Audio permission  is denied " +
                                    "please enable from app settings")
                        }
                    }
                }
            }

        }
    }
}

//prefers to request permission on resume /on start instead of on create/on view created
//if user launches- accepts and minimizes and denies and get from recents
//it wont be called runtime permission as we mentioned in on create
fun permissioncallcheck() {
}