package ir.maddev.payyourshare.data.repo.reference

import ir.maddev.payyourshare.data.model.local.TagLocal
import kotlinx.coroutines.flow.Flow

interface TagRepository {

    suspend fun save(tagLocal: TagLocal): Long

    suspend fun saveAll(tags: List<TagLocal>)

    suspend fun delete(tagLocal: TagLocal)

    suspend fun deleteById(tagId: Long)

    suspend fun getAll(): List<TagLocal>

    fun getAllStream(): Flow<List<TagLocal>>
}