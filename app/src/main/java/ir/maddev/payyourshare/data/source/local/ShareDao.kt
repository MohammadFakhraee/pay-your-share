package ir.maddev.payyourshare.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ir.maddev.payyourshare.data.model.local.ShareLocal

@Dao
interface ShareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(shareLocal: ShareLocal): Long

    @Delete
    suspend fun delete(shareLocal: ShareLocal)
}