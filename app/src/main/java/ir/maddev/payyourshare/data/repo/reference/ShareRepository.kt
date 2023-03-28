package ir.maddev.payyourshare.data.repo.reference

import ir.maddev.payyourshare.data.model.local.ShareLocal

interface ShareRepository {

    suspend fun save(shareLocal: ShareLocal): Long

    suspend fun saveAll(shares: List<ShareLocal>)

    suspend fun delete(shareLocal: ShareLocal)

    suspend fun deleteById(shareId: Long)

    suspend fun getAll(): List<ShareLocal>
}