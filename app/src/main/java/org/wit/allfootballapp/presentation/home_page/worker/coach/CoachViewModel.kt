package org.wit.allfootballapp.presentation.home_page.worker.coach

import org.wit.allfootballapp.presentation.home_page.info.InfoEvent
import org.wit.allfootballapp.presentation.home_page.info.InfoUiState



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.repository.CoachRepository
import org.wit.allfootballapp.domain.repository.TeamRepository
import org.wit.allfootballapp.presentation.home_page.info.InfoEvent.LoadInfo
import javax.inject.Inject

@HiltViewModel
class CoachViewModel @Inject  constructor(
    private val coachRepository: CoachRepository
): ViewModel(){

    private val _state = MutableStateFlow(CoachUiState(isLoading = false))
    val state: StateFlow<CoachUiState> = _state

    fun onEvent(event: CoachEvent){
        when(event){
            is CoachEvent.LoadCoach -> {
                loadInfo(event.teamId)
            }
        }
    }

    private fun loadInfo(teamId: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            try {
                val data = coachRepository.getCoachByTeam(teamId)
                _state.value = state.value.copy( coach = data, isLoading = false)
            } catch (e: Exception) {
                _state.value = state.value.copy(error = e.message, isLoading = false)
            }
        }
    }

}