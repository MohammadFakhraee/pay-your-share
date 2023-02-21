package ir.maddev.payyourshare.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ir.maddev.payyourshare.data.model.local.crossrefs.GroupPersonCrossRef

@Dao
interface GroupPersonCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(groupPersonCrossRef: GroupPersonCrossRef)

    @Delete
    suspend fun delete(groupPersonCrossRef: GroupPersonCrossRef)
}