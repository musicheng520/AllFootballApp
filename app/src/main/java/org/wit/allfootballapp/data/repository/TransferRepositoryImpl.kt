package org.wit.allfootballapp.data.repository

import org.wit.allfootballapp.data.mapper.toTransferInfo
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.domain.model.transfer.TransferInfo
import org.wit.allfootballapp.domain.repository.TransferRepository
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val api: FootballApi
) :TransferRepository {
    override suspend fun getTransferByTeam(teamId: Int): List<TransferInfo> {
        return api.getTransfers(teamId).toTransferInfo()
            ?: throw Exception("No transfer info found for teamId: $teamId")
    }
}