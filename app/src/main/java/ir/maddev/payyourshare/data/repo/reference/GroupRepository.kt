package ir.maddev.payyourshare.data.repo.reference

import ir.maddev.payyourshare.data.model.local.GroupLocal
import ir.maddev.payyourshare.data.model.local.relations.GroupWithPaymentsWithShares
import ir.maddev.payyourshare.data.model.local.relations.GroupWithPersons
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    suspend fun save(groupLocal: GroupLocal): Long

    suspend fun saveAll(groups: List<GroupLocal>)

    suspend fun getById(groupId: Long): GroupLocal

    fun getByIdStream(groupId: Long): Flow<GroupLocal>

    suspend fun delete(groupLocal: GroupLocal)

    suspend fun deleteById(groupId: Long)

    suspend fun getByIdWithPersons(groupId: Long): GroupWithPersons

    fun getByIdWithPersonsStream(groupId: Long): Flow<GroupWithPersons>

    suspend fun getByIdWithPayments(groupId: Long): GroupWithPaymentsWithShares

    fun getByIdWithPaymentsStream(groupId: Long): Flow<GroupWithPaymentsWithShares>

    suspend fun getAll(): List<GroupLocal>

    fun getAllStream(): Flow<List<GroupLocal>>

    suspend fun getAllWithPersons(): List<GroupWithPersons>

    fun getAllWithPersonsStream(): Flow<List<GroupWithPersons>>

    suspend fun getAllWithPayments(): List<GroupWithPaymentsWithShares>

    fun getAllWithPaymentsStream(): Flow<List<GroupWithPaymentsWithShares>>


}