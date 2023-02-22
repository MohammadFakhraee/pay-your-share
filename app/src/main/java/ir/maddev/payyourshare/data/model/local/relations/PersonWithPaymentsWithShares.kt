package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.PersonLocal

data class PersonWithPaymentsWithShares(
    @Embedded val personLocal: PersonLocal,
    @Relation(parentColumn = "person_id", entityColumn = "person_owner_id") val paymentsWithShares: List<PaymentWithShares>
)