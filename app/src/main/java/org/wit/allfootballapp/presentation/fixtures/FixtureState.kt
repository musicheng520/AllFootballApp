package org.wit.allfootballapp.presentation.fixtures

import org.wit.allfootballapp.data.remote.dto.fixture.Fixture
import org.wit.allfootballapp.domain.model.fixture.FixtureInfo

data class FixtureState(
    val fixtures: List<FixtureInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTeamId: Int? = null
)
