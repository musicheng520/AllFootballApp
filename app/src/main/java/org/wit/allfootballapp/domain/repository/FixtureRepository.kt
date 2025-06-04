package org.wit.allfootballapp.domain.repository

import org.wit.allfootballapp.domain.model.fixture.FixtureInfo

interface FixtureRepository {
    suspend fun getFixtureByTeam(teamId: Int, season: Int):List<FixtureInfo>
}