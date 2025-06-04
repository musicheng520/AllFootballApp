package org.wit.allfootballapp.data.local.user

import androidx.room.Database
import androidx.room.RoomDatabase
import org.wit.allfootballapp.domain.model.user.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}
