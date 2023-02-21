package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.GroupLocal
import ir.maddev.payyourshare.data.model.local.PaymentLocal

data class GroupWithPaymentsWithShares(
    @Embedded val groupLocal: GroupLocal,
    @Relation(
        entity = PaymentLocal::class,
        parentColumn = "groupId",
        entityColumn = "groupOwnerId"
    ) val paymentsWithShares: List<PaymentWithShares>
)