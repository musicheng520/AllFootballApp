package org.wit.allfootballapp.domain.usecase.app_entry

import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.manager.LocalUserManager


class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
     operator fun invoke(): Flow<Boolean>{
       return localUserManager.readAppEntry()
    }
}