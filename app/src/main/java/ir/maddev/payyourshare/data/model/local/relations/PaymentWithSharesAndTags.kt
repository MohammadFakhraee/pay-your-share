package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.ShareLocal
import ir.maddev.payyourshare.data.model.local.TagLocal
import ir.maddev.payyourshare.data.model.local.crossrefs.PaymentTagCrossRef

data class PaymentWithSharesAndTags(
    @Embedded val paymentLocal: PaymentLocal,
    @Relation(parentColumn = "payment_id", entityColumn = "payment_owner_id") val shareLocals: List<ShareLocal>,
    @Relation(parentColumn = "payment_id", entityColumn = "tag_id", associateBy = Junction(PaymentTagCrossRef::class)) val tags: List<TagLocal>
)