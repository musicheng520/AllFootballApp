package org.wit.allfootballapp.domain.model.team

data class VenueInfo(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val capacity: Int,
    val surface: String,
    val imageUrl: String
)
