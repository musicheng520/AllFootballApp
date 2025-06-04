package org.wit.allfootballapp.domain.model.team

data class TeamInfo(
    val id: Int,
    val name: String,
    val code: String?,
    val country: String,
    val founded: Int,
    val isNational: Boolean,
    val logoUrl: String,
    val venue: VenueInfo
)
