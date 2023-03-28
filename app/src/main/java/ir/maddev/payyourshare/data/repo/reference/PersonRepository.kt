package ir.maddev.payyourshare.data.repo.reference

import ir.maddev.payyourshare.data.model.local.PersonLocal
import ir.maddev.payyourshare.data.model.local.relations.PersonWithPaymentsWithSharesAndTags
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    suspend fun save(personLocal: PersonLocal): Long

    suspend fun saveAll(persons: List<PersonLocal>)

    suspend fun delete(personLocal: PersonLocal)

    suspend fun deleteById(personId: Long)

    suspend fun getById(personId: Long): PersonLocal

    fun getByIdStream(personId: Long): Flow<PersonLocal>

    suspend fun getAll(): List<PersonLocal>

    fun getAllStream(): Flow<List<PersonLocal>>

    suspend fun getAllWithPayments(): List<PersonWithPaymentsWithSharesAndTags>

    fun getAllWithPaymentsStream(): Flow<List<PersonWithPaymentsWithSharesAndTags>>

    suspend fun getByIdWithPayments(personId: Long): PersonWithPaymentsWithSharesAndTags

    fun getByIdWithPaymentsStream(personId: Long): Flow<PersonWithPaymentsWithSharesAndTags>
}