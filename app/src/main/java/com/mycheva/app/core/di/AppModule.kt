package com.mycheva.app.core.di

import android.content.Context
import com.mycheva.app.core.preferences.AppPreferences
import com.mycheva.app.core.preferences.dataStore
import com.mycheva.app.login.domain.LoginRepository
import com.mycheva.app.login.domain.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(requireNotNull(context.dataStore))
    }

    @Singleton
    @Provides
    fun provideLoginRepository(appPreferences: AppPreferences): LoginRepository {
        return LoginRepositoryImpl(appPreferences)
    }

}