package fr.mastersid.zitouni.stackoverflow.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fr.mastersid.zitouni.stackoverflow.R

@Composable
fun SendSmsButton(
    @RequiresPermission("android.permission.SEND_SMS") onPermissionGranted: ()-> Unit,
    onPermissionDenied: ()-> Unit,
    onPermissionNeedsExplanation: ()-> Unit
){
    val context = LocalContext.current
    Button(
        onClick={
            when{
                ContextCompat.checkSelfPermission(
                    context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                    -> {
                        onPermissionGranted()
                    }

                ActivityCompat.shouldShowRequestPermissionRationale(
                    context.getActivity(),
                    Manifest.permission.SEND_SMS
                ) -> {
                    onPermissionNeedsExplanation()
                }

                else -> {
                    //
                }

            }
        },
    ){
        Icon(
            Icons.Default.Email,
            contentDescription = stringResource(id= R.string.send_sms_button_description)
        )
    }
}
