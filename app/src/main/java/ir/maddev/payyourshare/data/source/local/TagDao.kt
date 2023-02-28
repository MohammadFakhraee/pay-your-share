package ir.maddev.payyourshare.data.source.local

import androidx.room.*
import ir.maddev.payyourshare.data.model.local.TagLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(tag: TagLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(tags: List<TagLocal>)

    @Delete
    suspend fun delete(tag: TagLocal)

    @Query("DELETE FROM tags WHERE tag_id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM tags")
    suspend fun getAll(): List<TagLocal>

    @Query("SELECT * FROM tags")
    fun getAllStream(): Flow<List<TagLocal>>
}