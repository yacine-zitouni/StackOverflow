package fr.mastersid.zitouni.stackoverflow.service

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SmsServiceModule {
    @Binds
    abstract fun bindSmsService(smsServiceImpl: SmsServiceImpl): SmsService
}