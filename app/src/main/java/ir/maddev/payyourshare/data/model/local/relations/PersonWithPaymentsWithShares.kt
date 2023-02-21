package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.PersonLocal

data class PersonWithPaymentsWithShares(
    @Embedded val personLocal: PersonLocal,
    @Relation(parentColumn = "personId", entityColumn = "personOwnerId") val paymentsWithShares: List<PaymentWithShares>
)