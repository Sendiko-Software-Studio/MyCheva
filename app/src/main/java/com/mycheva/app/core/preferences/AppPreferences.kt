package com.mycheva.app.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val tokenKey = stringPreferencesKey("token")
    private val userIdKey = stringPreferencesKey("userId")

    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[userIdKey] = userId
        }
    }

    fun getUserId(): Flow<String> {
        return dataStore.data.map {
            it[userIdKey]?:""
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    suspend fun deleteToken() {
        dataStore.edit { preferences ->
            preferences[tokenKey] = ""
            preferences[userIdKey] = ""
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[tokenKey]?:""
        }
    }


}