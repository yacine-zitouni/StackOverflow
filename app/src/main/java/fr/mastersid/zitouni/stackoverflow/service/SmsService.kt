package fr.mastersid.zitouni.stackoverflow.service

import androidx.annotation.RequiresPermission

interface SmsService {

    @RequiresPermission("android.permission.SEND_SMS")
    suspend fun sendSms(message: String)
}