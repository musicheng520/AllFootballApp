package org.wit.allfootballapp.presentation.fixtures

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import org.wit.allfootballapp.domain.repository.FixtureRepository
import javax.inject.Inject

@HiltViewModel
class FixtureViewModel @Inject constructor(
    private val repository: FixtureRepository // 你的仓库接口
): ViewModel() {

    private val _state = MutableStateFlow(FixtureState(isLoading = true))
    val state: StateFlow<FixtureState> = _state

    fun loadFixtures(teamId: Int) {
        viewModelScope.launch {
            try {
                _state.value = FixtureState(isLoading = true)
                val fixtures = repository.getFixtureByTeam(teamId,2023)
                _state.value = FixtureState(fixtures = fixtures)
            } catch(e: Exception) {
                _state.value = FixtureState(error = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private var _eventHandler: ((FixturesEvent) -> Unit)? = null

    fun setEventHandler(handler: (FixturesEvent) -> Unit) {
        _eventHandler = handler
    }

    fun onEvent(event: FixturesEvent) {
        when(event) {
            is FixturesEvent.OnTeamClicked -> {
                _eventHandler?.invoke(event)
            }
            else -> {}
        }
    }
}
