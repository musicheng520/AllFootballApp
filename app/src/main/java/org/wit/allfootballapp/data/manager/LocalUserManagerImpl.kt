package org.wit.allfootballapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.util.Constants

class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }

    override suspend fun saveLoginStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.IS_LOGGED_IN] = isLoggedIn
        }
    }

    override fun readLoginStatus(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] ?: false
        }
    }


           override suspend fun clearUserData() {
               context.dataStore.edit { settings ->
                   settings.remove(PreferencesKeys.IS_LOGGED_IN)
                   settings.remove(PreferencesKeys.USER_ID)
                   settings.remove(PreferencesKeys.TEAM_ID)
                   settings.remove(PreferencesKeys.APP_ENTRY)
               }
           }

    override suspend fun saveUserId(id: Int) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.USER_ID] = id
        }
    }

    override fun readUserId(): Flow<Int?> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.USER_ID]
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    val TEAM_ID = intPreferencesKey(Constants.TEAM_ID)
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val USER_ID = intPreferencesKey("user_id")  // 新增userId的key
}
