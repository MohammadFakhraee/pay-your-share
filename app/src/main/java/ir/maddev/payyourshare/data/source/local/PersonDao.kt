package ir.maddev.payyourshare.data.source.local

import androidx.room.*
import ir.maddev.payyourshare.data.model.local.PersonLocal
import ir.maddev.payyourshare.data.model.local.relations.PersonWithPaymentsWithSharesAndTags
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object used for [PersonLocal]
 */
@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(personLocal: PersonLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(persons: List<PersonLocal>)

    @Delete
    suspend fun delete(personLocal: PersonLocal)

    @Query("SELECT * FROM persons")
    suspend fun getAll(): List<PersonLocal>

    @Query("SELECT * FROM persons")
    fun getAllStream(): Flow<List<PersonLocal>>

    @Transaction
    @Query("SELECT * FROM persons")
    suspend fun getAllPersonsWithPaymentsWithSharesAndTags(): List<PersonWithPaymentsWithSharesAndTags>

    @Transaction
    @Query("SELECT * FROM persons")
    fun getAllPersonsWithPaymentsWithSharesAndTagsStream(): Flow<List<PersonWithPaymentsWithSharesAndTags>>

    @Query("SELECT * FROM persons WHERE person_id = :id")
    suspend fun getById(id: Long): PersonLocal

    @Query("SELECT * FROM persons WHERE person_id = :id")
    fun getByIdStream(id: Long): Flow<PersonLocal>

    @Query("DELETE FROM persons WHERE person_id = :id")
    suspend fun deleteById(id: Long)

    @Transaction
    @Query("SELECT * FROM persons WHERE person_id = :id")
    suspend fun getPersonWithPaymentsWithSharesAndTagsById(id: Long): PersonWithPaymentsWithSharesAndTags

    @Transaction
    @Query("SELECT * FROM persons WHERE person_id = :id")
    fun getPersonWithPaymentsWithSharesAndTagsByIdStream(id: Long): Flow<PersonWithPaymentsWithSharesAndTags>
}