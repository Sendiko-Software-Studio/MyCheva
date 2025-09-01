package com.mycheva.app.core.di

import com.mycheva.app.core.database.RoomFactory
import com.mycheva.app.core.network.HttpClientFactory
import com.mycheva.app.core.network.KtorClient
import com.mycheva.app.core.preferences.DatastoreFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val koinModules = module {
    single<HttpClientEngine> { OkHttp.create() }

    single { HttpClientFactory.create(get()) }
    single { RoomFactory.create(androidContext()) }
    single { DatastoreFactory.create(androidContext()) }
    single { KtorClient(get()) }
}