package fr.mastersid.zitouni.stackoverflow.usecase

import androidx.annotation.RequiresPermission
import fr.mastersid.zitouni.stackoverflow.service.SmsService
import javax.inject.Inject


class SendSmsUsecase @Inject constructor (
    private val smsService : SmsService
) {
    @RequiresPermission("android.permission.SEND_SMS")
    suspend operator fun invoke(message: String){
        smsService.sendSms(message)
    }
}