package ir.maddev.payyourshare.data.source.local

import androidx.room.*
import ir.maddev.payyourshare.data.model.local.GroupLocal
import ir.maddev.payyourshare.data.model.local.relations.GroupWithPaymentsWithShares
import ir.maddev.payyourshare.data.model.local.relations.GroupWithPersons
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(groupLocal: GroupLocal): Long

    @Delete
    suspend fun delete(groupLocal: GroupLocal)

    @Query("SELECT * FROM GroupLocal")
    suspend fun getAll(): List<GroupLocal>

    @Query("SELECT * FROM GroupList")
    fun getAllStream(): Flow<List<GroupLocal>>

    @Query("SELECT * FROM GroupLocal")
    suspend fun getAllGroupsWithPersons(): List<GroupWithPersons>

    @Query("SELECT * FROM GroupLocal")
    fun getAllGroupsWithPersonsStream(): Flow<List<GroupWithPersons>>

    @Query("SELECT * FROM GroupLocal")
    suspend fun getAllGroupsWithPaymentsWithShares(): List<GroupWithPaymentsWithShares>

    @Query("SELECT * FROM GroupLocal")
    fun getAllGroupsWithPaymentsWithSharesStream(): Flow<List<GroupWithPaymentsWithShares>>

    @Query("SELECT * FROM GroupLocal WHERE groupId = :id")
    suspend fun getById(id: Long): GroupLocal

    @Query("SELECT * FROM GroupLocal WHERE groupId = :id")
    fun getByIdStream(id: Long): Flow<GroupLocal>

    @Query("SELECT * FROM GroupLocal WHERE groupId = :id")
    suspend fun getByIdWithPersons(id: Long): GroupWithPersons

    @Query("SELECT * FROM GroupLocal WHERE groupId = :id")
    fun getByIdWithPersonsStream(id: Long): Flow<GroupWithPersons>

    @Query("SELECT * FROM GroupLocal WHERE groupId = :id")
    suspend fun getByIdWithPaymentsWithShares(id: Long): GroupWithPaymentsWithShares

    @Query("SELECT * FROM GroupLocal WHERE groupId = :id")
    fun getByIdWithPaymentsWithSharesStream(id: Long): Flow<GroupWithPaymentsWithShares>
}