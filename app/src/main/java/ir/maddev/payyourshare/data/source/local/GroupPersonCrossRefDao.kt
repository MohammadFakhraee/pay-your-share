package ir.maddev.payyourshare.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ir.maddev.payyourshare.data.model.local.GroupLocal
import ir.maddev.payyourshare.data.model.local.PersonLocal
import ir.maddev.payyourshare.data.model.local.crossrefs.GroupPersonCrossRef

/**
 * Data Access Object for many-to-many relation between [GroupLocal] and [PersonLocal]
 */
@Dao
interface GroupPersonCrossRefDao {

    @Insert
    suspend fun save(groupPersonCrossRef: GroupPersonCrossRef)

    @Insert
    suspend fun saveAll(groupPersonCrossRefs: List<GroupPersonCrossRef>)

    @Delete
    suspend fun delete(groupPersonCrossRef: GroupPersonCrossRef)

    @Query("DELETE FROM group_person WHERE group_id = :id")
    suspend fun deleteByGroupId(id: Long)

    @Query("DELETE FROM group_person WHERE person_id = :id")
    suspend fun deleteByPersonId(id: Long)

    // todo: Add deleteByGroupAndPersonId function

    @Query("SELECT * FROM group_person")
    suspend fun getAll(): List<GroupPersonCrossRef>
}