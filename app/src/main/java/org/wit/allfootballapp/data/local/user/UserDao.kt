package org.wit.allfootballapp.data.local.user

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.model.user.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE userId = :id")
    suspend fun getUserById(id: Int): User?

    @Query("SELECT * FROM users WHERE username = :name")
    suspend fun getUserByName(name: String): User?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUserByCredentials(email: String, password: String): User?

    @Query("SELECT * FROM users WHERE email =:email")
    suspend fun getUserByEmail(email: String): User?

}

