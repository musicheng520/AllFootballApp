package org.wit.allfootballapp.presentation.home_page.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.domain.repository.TeamRepository
import org.wit.allfootballapp.presentation.home_page.info.InfoEvent.LoadInfo
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject  constructor(
   private val teamRepository: TeamRepository,
    private val localUserManager: LocalUserManager
): ViewModel(){


    private val _state = MutableStateFlow(InfoUiState(isLoading = false))
    val state: StateFlow<InfoUiState> = _state

    fun onEvent(event: InfoEvent){
        when(event){
            is InfoEvent.LoadInfo -> {
                loadInfo(event.teamId)
            }
        }
    }

    private fun loadInfo(teamId: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            try {
                val data = teamRepository.getTeamInfo(teamId)
                _state.value = state.value.copy( teamInfo = data, isLoading = false)
            } catch (e: Exception) {
                _state.value = state.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            localUserManager.clearUserData()
            localUserManager.saveLoginStatus(false)
            onComplete()
        }
    }


}