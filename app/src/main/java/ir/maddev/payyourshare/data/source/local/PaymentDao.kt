package ir.maddev.payyourshare.data.source.local

import androidx.room.*
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.relations.PaymentWithSharesAndTags

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(paymentLocal: PaymentLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(payments: List<PaymentLocal>)

    @Delete
    suspend fun delete(paymentLocal: PaymentLocal)

    @Query("DELETE FROM payments WHERE payment_id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM payments")
    suspend fun getAll(): List<PaymentLocal>

    @Transaction
    @Query("SELECT * FROM payments")
    suspend fun getAllPaymentsWithSharesAndTags(): List<PaymentWithSharesAndTags>
}