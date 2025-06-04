package org.wit.allfootballapp.domain.repository

import org.wit.allfootballapp.domain.model.transfer.TransferInfo

interface TransferRepository {
    suspend fun getTransferByTeam(teamId: Int):List<TransferInfo>
}