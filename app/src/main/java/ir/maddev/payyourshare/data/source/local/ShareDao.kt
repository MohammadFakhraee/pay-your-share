package ir.maddev.payyourshare.data.source.local

import androidx.room.*
import ir.maddev.payyourshare.data.model.local.ShareLocal

@Dao
interface ShareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(shareLocal: ShareLocal): Long

    @Delete
    suspend fun delete(shareLocal: ShareLocal)

    @Query("SELECT * FROM ShareLocal")
    suspend fun getAll(): List<ShareLocal>
}