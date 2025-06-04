package org.wit.allfootballapp.presentation.home_page.info

import org.wit.allfootballapp.domain.model.team.TeamInfo


data class InfoUiState(
    val isLoading: Boolean = false,
    val teamInfo: TeamInfo ?= null,
    val error: String? = null
)
