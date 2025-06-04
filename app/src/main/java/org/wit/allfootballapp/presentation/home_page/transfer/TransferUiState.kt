package org.wit.allfootballapp.presentation.home_page.transfer

import org.wit.allfootballapp.domain.model.transfer.TransferInfo

data class TransferUiState(
    val isLoading: Boolean = false,
    val transfers: List<TransferInfo> = emptyList(),
    val error: String? = null
)
