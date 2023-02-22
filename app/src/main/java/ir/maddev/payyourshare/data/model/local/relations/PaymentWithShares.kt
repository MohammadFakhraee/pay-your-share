package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.ShareLocal

data class PaymentWithShares(
    @Embedded val paymentLocal: PaymentLocal,
    @Relation(parentColumn = "payment_id", entityColumn = "payment_owner_id") val shareLocals: List<ShareLocal>
)