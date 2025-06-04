package org.wit.allfootballapp.presentation.fixtures

sealed class FixturesEvent {
    data class OnTeamClicked(val teamId: Int) : FixturesEvent()
    object LoadFixtures : FixturesEvent()
}