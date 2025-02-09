package fr.mastersid.zitouni.stackoverflow.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.mastersid.zitouni.stackoverflow.R
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.ui.theme.StackOverflowTheme
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun QuestionRow (question: Question,
                 onPermissionGranted:(message: String)-> Unit,
                 onPermissionDenied:()-> Unit,
                 onPermissionNeedsExplanation:(()-> Unit)-> Unit)
{
    val context = LocalContext.current

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            isGranted:Boolean ->
               if (isGranted) {
                    onPermissionGranted(question.title)
               }
               else {
                    onPermissionDenied()
               }

        }
    Row( horizontalArrangement = Arrangement.spacedBy( 16.dp ),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = question.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text( text = question.answerCount.toString())
        Button(
            onClick={
                when{
                    ContextCompat.checkSelfPermission(
                        context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                        -> {
                        onPermissionGranted(question.title)
                    }

                    ActivityCompat.shouldShowRequestPermissionRationale(
                        context.getActivity(),
                        Manifest.permission.SEND_SMS
                    ) -> {
                        onPermissionNeedsExplanation{
                            requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
                        }
                    }

                    else -> {
                        requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)

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
}


fun Context.getActivity(): Activity {
    var currentContext = this
    while ( currentContext is ContextWrapper) {
        if ( currentContext is Activity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    throw IllegalStateException (" Permissions should be called in the context of an Activity ")
}