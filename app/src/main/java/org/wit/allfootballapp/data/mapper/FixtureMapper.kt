package org.wit.allfootballapp.data.mapper

import org.wit.allfootballapp.data.remote.dto.fixture.FixtureDto
import org.wit.allfootballapp.data.remote.dto.fixture.Response
import org.wit.allfootballapp.domain.model.fixture.FixtureInfo
import org.wit.allfootballapp.domain.model.fixture.GoalInfo
import org.wit.allfootballapp.domain.model.fixture.ScoreInfo
import org.wit.allfootballapp.domain.model.fixture.TeamInfo
import org.wit.allfootballapp.domain.model.fixture.VenueInfo

fun FixtureDto.toFixtureInfo(): List<FixtureInfo> {
    return response.map { it.toFixtureInfo() }
}
fun Response.toFixtureInfo(): FixtureInfo {
    return FixtureInfo(
        fixtureId = fixture.id,
        date = fixture.date,
        status = fixture.status.short ?: fixture.status.long ?: "",
        venue = VenueInfo(
            id = fixture.venue.id,
            name = fixture.venue.name,
            city = fixture.venue.city
        ),
        referee = fixture.referee,
        homeTeam = TeamInfo(
            id = teams.home.id,
            name = teams.home.name,
            logo = teams.home.logo,
            winner = teams.home.winner
        ),
        awayTeam = TeamInfo(
            id = teams.away.id,
            name = teams.away.name,
            logo = teams.away.logo,
            winner = teams.away.winner
        ),
        goals = GoalInfo(
            home = goals.home,
            away = goals.away
        ),
        score = ScoreInfo(
            halftime = GoalInfo(
                home = score.halftime.home,
                away = score.halftime.away
            ),
            fulltime = GoalInfo(
                home = score.fulltime.home,
                away = score.fulltime.away
            ),
            extratime = score.extratime?.let {
                GoalInfo(home = it.home, away = it.away)
            },
            penalty = score.penalty?.let {
                GoalInfo(home = it.home, away = it.away)
            }
        )
    )
}
