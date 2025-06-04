package org.wit.allfootballapp.data.mapper

import org.wit.allfootballapp.data.remote.dto.team.TeamDto
import org.wit.allfootballapp.data.remote.dto.team.Venue
import org.wit.allfootballapp.domain.model.team.TeamInfo
import org.wit.allfootballapp.domain.model.team.VenueInfo

fun TeamDto.toTeamInfo():TeamInfo?  {

    return response.firstOrNull()?.let {
        TeamInfo(
            id = it.team.id,
            name = it.team.name,
            country = it.team.country,
            founded = it.team.founded,
            logoUrl = it.team.logo,
            isNational = it.team.national,
            code = it.team.code,
            venue = VenueInfo(
                id = it.venue.id,
                name = it.venue.name,
                city = it.venue.city,
                capacity = it.venue.capacity,
                imageUrl = it.venue.image,
                surface = it.venue.surface,
                address = it.venue.address
            )
        )
    }
}

