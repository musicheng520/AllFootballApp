package org.wit.allfootballapp.presentation.home_page.transfer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.repository.TransferRepository
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val transferRepository: TransferRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TransferUiState(isLoading = false))
    val state: StateFlow<TransferUiState> = _state

    fun onEvent(event: TransferEvent) {
        when (event) {
            is TransferEvent.LoadTransfers -> {
                loadTransfers(event.teamId)
            }
        }
    }

    private fun loadTransfers(teamId: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            try {
                val data = transferRepository.getTransferByTeam(teamId)
                _state.value = state.value.copy(transfers = data, isLoading = false)
            } catch (e: Exception) {
                _state.value = state.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}
