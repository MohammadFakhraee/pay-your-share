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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(groupLocals: List<GroupLocal>)

    @Delete
    suspend fun delete(groupLocal: GroupLocal)

    @Query("SELECT * FROM groups")
    suspend fun getAll(): List<GroupLocal>

    @Query("SELECT * FROM groups")
    fun getAllStream(): Flow<List<GroupLocal>>

    @Transaction
    @Query("SELECT * FROM groups")
    suspend fun getAllGroupsWithPersons(): List<GroupWithPersons>

    @Transaction
    @Query("SELECT * FROM groups")
    fun getAllGroupsWithPersonsStream(): Flow<List<GroupWithPersons>>

    @Transaction
    @Query("SELECT * FROM groups")
    suspend fun getAllGroupsWithPaymentsWithShares(): List<GroupWithPaymentsWithShares>

    @Transaction
    @Query("SELECT * FROM groups")
    fun getAllGroupsWithPaymentsWithSharesStream(): Flow<List<GroupWithPaymentsWithShares>>

    @Query("SELECT * FROM groups WHERE group_id = :id")
    suspend fun getById(id: Long): GroupLocal

    @Query("SELECT * FROM groups WHERE group_id = :id")
    fun getByIdStream(id: Long): Flow<GroupLocal>

    @Query("DELETE FROM groups WHERE group_id = :id")
    suspend fun deleteById(id: Long)

    @Transaction
    @Query("SELECT * FROM groups WHERE group_id = :id")
    suspend fun getByIdWithPersons(id: Long): GroupWithPersons

    @Transaction
    @Query("SELECT * FROM groups WHERE group_id = :id")
    fun getByIdWithPersonsStream(id: Long): Flow<GroupWithPersons>

    @Transaction
    @Query("SELECT * FROM groups WHERE group_id = :id")
    suspend fun getByIdWithPaymentsWithShares(id: Long): GroupWithPaymentsWithShares

    @Transaction
    @Query("SELECT * FROM groups WHERE group_id = :id")
    fun getByIdWithPaymentsWithSharesStream(id: Long): Flow<GroupWithPaymentsWithShares>
}