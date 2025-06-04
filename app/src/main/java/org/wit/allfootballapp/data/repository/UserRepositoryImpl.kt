package org.wit.allfootballapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.data.local.user.UserDao
import org.wit.allfootballapp.domain.model.user.User
import org.wit.allfootballapp.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(): Flow<List<User>> = userDao.getUsers()

    override suspend fun getUserById(id: Int): User? = userDao.getUserById(id)

    override suspend fun upsertUser(user: User) = userDao.upsert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun getUserByCredentials(email: String, password: String): User? =
        userDao.getUserByCredentials(email, password)

    override suspend fun getUserByName(username: String): User? = userDao.getUserByName(username)

    override suspend fun getUserByEmail(email: String): User? =userDao.getUserByEmail(email)
    override suspend fun getUserTeamId(userId: Int): Int? {
       val user = userDao.getUserById(userId)
        return user?.teamId
    }

}
