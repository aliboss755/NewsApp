package com.loc.newsapp.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domin.manger.LocalUserManger
import com.loc.newsapp.util.Constants.APP_ENTRY
import com.loc.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImpl(
    private val context: Context
):LocalUserManger {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings->
           settings[PreferncesKey.App_ENTRY] =true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferncesKey.App_ENTRY] ?:false
        }
    }
}
private val Context.dataStore :DataStore<Preferences> by preferencesDataStore(name =USER_SETTINGS )
private object PreferncesKey{
val App_ENTRY= booleanPreferencesKey(name=APP_ENTRY)
}