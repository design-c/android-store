package com.example.feature_profile.data

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_profile")

object ProfileDataStore {
    private val NAME_KEY = stringPreferencesKey("name")
    private val EMAIL_KEY = stringPreferencesKey("email")
    private val PHONE_KEY = stringPreferencesKey("phone")
    private val ADDRESS_KEY = stringPreferencesKey("address")

    suspend fun saveProfile(context: Context, name: String, email: String, phone: String, address: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[EMAIL_KEY] = email
            preferences[PHONE_KEY] = phone
            preferences[ADDRESS_KEY] = address
        }
    }

    fun getProfile(context: Context): Flow<UserProfile> {
        return context.dataStore.data.map { preferences ->
            UserProfile(
                name = preferences[NAME_KEY] ?: "",
                email = preferences[EMAIL_KEY] ?: "",
                phone = preferences[PHONE_KEY] ?: "",
                address = preferences[ADDRESS_KEY] ?: ""
            )
        }
    }
}

data class UserProfile(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = ""
)