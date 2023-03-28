package ir.maddev.payyourshare.data.repo.reference

import ir.maddev.payyourshare.data.model.local.PaymentLocal
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    suspend fun save(paymentLocal: PaymentLocal): Long

    suspend fun saveAll(payments: List<PaymentLocal>)

    suspend fun delete(paymentLocal: PaymentLocal)

    suspend fun deleteById(paymentId: Long)

    suspend fun getAll(): List<PaymentLocal>

    fun getAllStream(): Flow<List<PaymentLocal>>
}