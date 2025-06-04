package org.wit.allfootballapp.presentation.login.choose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.domain.model.user.User
import org.wit.allfootballapp.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class ChooseTeamViewModel @Inject constructor(
    private val localUserManager: LocalUserManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _teamId = MutableStateFlow<Int?>(null)
    val teamId = _teamId.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isTeamSaved = MutableStateFlow(false)
    val isTeamSaved = _isTeamSaved.asStateFlow()

    init {
        readSavedTeamId()
    }

    fun saveTeamId(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true

            val userId = localUserManager.readUserId().firstOrNull()
            if (userId != null) {
                val user = userRepository.getUserById(userId)
                if (user != null) {
                    userRepository.upsertUser(user.copy(teamId = id))
                    _teamId.value = id
                    _isTeamSaved.value = true
                } else {
                    // 用户不存在的处理（比如提示或重定向登录）
                }
            } else {
                // 用户ID为空的处理（比如提示或重定向登录）
            }

            _isLoading.value = false
        }
    }


    private fun readSavedTeamId() {
        viewModelScope.launch {
            _isLoading.value = true

            val userId = localUserManager.readUserId().firstOrNull()
            val teamId = if (userId != null) {
                userRepository.getUserTeamId(userId)
            } else null

            _teamId.value = teamId
            _isLoading.value = false
        }
    }
}
