
package org.wit.allfootballapp.domain.usecase.app_entry
import org.wit.allfootballapp.domain.manager.LocalUserManager


class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}