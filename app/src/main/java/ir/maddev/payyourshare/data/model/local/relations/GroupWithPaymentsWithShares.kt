package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.GroupLocal
import ir.maddev.payyourshare.data.model.local.PaymentLocal

data class GroupWithPaymentsWithShares(
    @Embedded val groupLocal: GroupLocal,
    @Relation(
        entity = PaymentLocal::class,
        parentColumn = "group_id",
        entityColumn = "group_owner_id"
    ) val paymentsWithShares: List<PaymentWithSharesAndTags>
)