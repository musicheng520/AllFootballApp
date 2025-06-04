package org.wit.allfootballapp.presentation.home_page.worker.coach

sealed class CoachEvent {
    data class LoadCoach(val teamId: Int) : CoachEvent()
}
