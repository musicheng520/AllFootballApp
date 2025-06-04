package org.wit.allfootballapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>

    suspend fun saveUserId(id: Int)
    fun readUserId(): Flow<Int?>

    suspend fun saveLoginStatus(isLoggedIn: Boolean)
     fun readLoginStatus(): Flow<Boolean>


    suspend fun clearUserData()
}
