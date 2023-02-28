package ir.maddev.payyourshare.data.source.local

import androidx.room.*
import ir.maddev.payyourshare.data.model.local.ShareLocal

@Dao
interface ShareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(shareLocal: ShareLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(payments: List<ShareLocal>)

    @Delete
    suspend fun delete(shareLocal: ShareLocal)

    @Query("DELETE FROM shares WHERE share_id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM shares")
    suspend fun getAll(): List<ShareLocal>
}