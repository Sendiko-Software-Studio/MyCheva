package com.mycheva.app.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

object DatastoreFactory {
    fun create(context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}
