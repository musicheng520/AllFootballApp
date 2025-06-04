package org.wit.allfootballapp.domain.model.fixture

data class FixtureInfo(
    val fixtureId: Int,
    val date: String,
    val status: String,
    val venue: VenueInfo,
    val referee: String?,
    val homeTeam: TeamInfo,
    val awayTeam: TeamInfo,
    val goals: GoalInfo,
    val score: ScoreInfo
)

data class VenueInfo(
    val id: Int?,
    val name: String?,
    val city: String?
)

data class TeamInfo(
    val id: Int,
    val name: String,
    val logo: String,
    val winner: Boolean?
)

data class GoalInfo(
    val home: Int?,
    val away: Int?
)

data class ScoreInfo(
    val halftime: GoalInfo,
    val fulltime: GoalInfo,
    val extratime: GoalInfo?,
    val penalty: GoalInfo?
)
