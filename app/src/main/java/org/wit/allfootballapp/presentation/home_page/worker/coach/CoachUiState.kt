package org.wit.allfootballapp.presentation.home_page.worker.coach

import org.wit.allfootballapp.domain.model.coach.CoachInfo


data class CoachUiState(
    val isLoading: Boolean = false,
    val coach: CoachInfo ?=null,
    val error: String? = null
)