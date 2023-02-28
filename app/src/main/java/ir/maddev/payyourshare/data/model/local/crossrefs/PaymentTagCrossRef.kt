package ir.maddev.payyourshare.data.model.local.crossrefs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "payment_tag", primaryKeys = ["payment_id", "tag_id"], indices = [Index(value = arrayOf("tag_id"))])
data class PaymentTagCrossRef(
    @ColumnInfo(name = "payment_id") var paymentId: Long = 0, @ColumnInfo(name = "tag_id") var tagId: Long = 0
)