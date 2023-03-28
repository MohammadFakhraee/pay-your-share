package ir.maddev.payyourshare.data.repo.reference

import ir.maddev.payyourshare.data.model.local.crossrefs.PaymentTagCrossRef

interface PaymentTagRepository {

    suspend fun save(paymentTagCrossRef: PaymentTagCrossRef)

    suspend fun saveAll(paymentTags: List<PaymentTagCrossRef>)

    suspend fun delete(paymentTagCrossRef: PaymentTagCrossRef)

    suspend fun deleteByPaymentId(paymentId: Long)

    suspend fun getAll(): List<PaymentTagCrossRef>
}