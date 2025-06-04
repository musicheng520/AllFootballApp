package org.wit.allfootballapp.domain.model.coach

data class CoachCareer(
    val teamId: Int,
    val teamName: String,
    val logoUrl: String,
    val startDate: String,
    val endDate: String?
)

