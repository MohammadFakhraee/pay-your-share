package ir.maddev.payyourshare.data.model.local.crossrefs

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["payment_id", "tag_id"])
data class PaymentTagCrossRef(
    @ColumnInfo("payment_id") var paymentId: Long = 0,
    @ColumnInfo("tag_id") var tagId: Long = 0
)