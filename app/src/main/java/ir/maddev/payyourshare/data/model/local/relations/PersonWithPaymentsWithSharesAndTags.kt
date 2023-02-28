package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.PersonLocal

data class PersonWithPaymentsWithSharesAndTags(
    @Embedded val personLocal: PersonLocal, @Relation(
        entity = PaymentLocal::class, parentColumn = "person_id", entityColumn = "person_owner_id"
    ) val paymentsWithSharesAndTags: List<PaymentWithSharesAndTags>
)