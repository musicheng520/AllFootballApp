package org.wit.allfootballapp.domain.model.coach

data class CoachInfo(
    val id: Int,
    val name: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val birthDate: String,
    val birthPlace: String,
    val birthCountry: String,
    val nationality: String,
    val height: String?,  // 有些为空
    val weight: String?,  // 有些为空
    val photoUrl: String,
    val teamId: Int,
    val teamName: String,
    val teamLogo: String,
    val career: List<CoachCareer>
)
