package ir.maddev.payyourshare.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ir.maddev.payyourshare.data.model.local.crossrefs.PaymentTagCrossRef

/**
 * The Data Access Object for many-to-many relation between [PaymentLocal] and [TagLocal]
 */
@Dao
interface PaymentTagCrossRefDao {

    @Insert
    suspend fun save(paymentTagCrossRef: PaymentTagCrossRef)

    @Insert
    suspend fun saveAll(paymentTagCrossRefs: List<PaymentTagCrossRef>)

    @Delete
    suspend fun delete(paymentTagCrossRef: PaymentTagCrossRef)

    @Query("DELETE FROM payment_tag WHERE payment_id = :id")
    suspend fun deleteByPaymentId(id: Long)

    // todo: Add deleteByTagId function

    // todo: Add deleteByPaymentAndTagId function

    @Query("SELECT * FROM payment_tag")
    suspend fun getAll(): List<PaymentTagCrossRef>
}