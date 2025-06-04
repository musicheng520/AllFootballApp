package org.wit.allfootballapp.presentation.home_page.info

sealed class InfoEvent{
    data class LoadInfo(val teamId: Int) : InfoEvent()
}
