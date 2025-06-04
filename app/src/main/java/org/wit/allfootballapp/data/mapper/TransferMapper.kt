package org.wit.allfootballapp.data.mapper

import org.wit.allfootballapp.data.remote.dto.transfer.Response
import org.wit.allfootballapp.data.remote.dto.transfer.TransferDto
import org.wit.allfootballapp.domain.model.transfer.Player
import org.wit.allfootballapp.domain.model.transfer.TeamInfo
import org.wit.allfootballapp.domain.model.transfer.Transfer
import org.wit.allfootballapp.domain.model.transfer.TransferInfo
import org.wit.allfootballapp.domain.model.transfer.TransferTeams


fun TransferDto.toTransferInfo(): List<TransferInfo> {
    return response.map { res ->
        TransferInfo(
            player = Player(
                id = res.player.id,
                name = res.player.name
            ),
            transfers = res.transfers
                .filter { it.date.startsWith("2023") } // 只保留2023年的转会
                .map { transfer ->
                    Transfer(
                        date = transfer.date,
                        type = transfer.type,
                        teams = TransferTeams(
                            `in` = TeamInfo(
                                id = transfer.teams.inX.id,
                                name = transfer.teams.inX.name,
                                logo = transfer.teams.inX.logo
                            ),
                            `out` = TeamInfo(
                                id = transfer.teams.out.id,
                                name = transfer.teams.out.name,
                                logo = transfer.teams.out.logo
                            )
                        )
                    )
                }
        )
    }.filter { it.transfers.isNotEmpty() } // 排除没有2023转会记录的球员
}

