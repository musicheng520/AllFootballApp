package org.wit.allfootballapp.domain.model.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val email: String,
    val username: String,
    val password: String,
    val teamId: Int? = null // 用户选择的球队，可为空
) : Parcelable

