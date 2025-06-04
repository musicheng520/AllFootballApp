package org.wit.allfootballapp.presentation.home_page.transfer


sealed class TransferEvent {
    data class LoadTransfers(val teamId: Int) : TransferEvent()
}

