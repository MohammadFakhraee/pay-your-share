package ir.maddev.payyourshare.data.source.local

import androidx.room.*
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.relations.PaymentWithShares

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(paymentLocal: PaymentLocal): Long

    @Delete
    suspend fun delete(paymentLocal: PaymentLocal)

    @Query("SELECT * FROM PaymentLocal")
    suspend fun getAll(): List<PaymentWithShares>
}