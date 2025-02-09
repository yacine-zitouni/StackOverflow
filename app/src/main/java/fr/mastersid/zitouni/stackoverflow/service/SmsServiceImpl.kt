package fr.mastersid.zitouni.stackoverflow.service

import android.content.Context
import android.telephony.SmsManager
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SmsServiceImpl @Inject constructor(@ApplicationContext private val context: Context) :SmsService{

    override suspend fun sendSms(message: String){
        val smsManager = ContextCompat.getSystemService ( context , SmsManager :: class . java )
        smsManager ?. sendTextMessage (" 5554 " , null , message , null , null )
    }
}