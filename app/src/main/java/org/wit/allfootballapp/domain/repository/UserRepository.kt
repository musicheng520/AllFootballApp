package org.wit.allfootballapp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.model.user.User

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun getUserById(id: Int): User?
    suspend fun upsertUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getUserByCredentials(email: String, password: String): User?  // 加这一条
    suspend fun getUserByName(username: String): User?
    suspend fun getUserByEmail(email: String): User?

    suspend fun getUserTeamId(userId: Int): Int?
}
