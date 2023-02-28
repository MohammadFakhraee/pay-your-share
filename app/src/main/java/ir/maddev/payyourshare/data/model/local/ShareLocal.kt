package ir.maddev.payyourshare.data.model.local

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "shares",
    foreignKeys = [
        ForeignKey(
            entity = PaymentLocal::class,
            parentColumns = ["payment_id"],
            childColumns = ["payment_owner_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ],
    indices = [Index("payment_owner_id")]
)
data class ShareLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "share_id") var id: Long = 0,
    @ColumnInfo(name = "payment_owner_id") var paymentOwnerId: Long = 0,
    @ColumnInfo(name = "person_owner_id") var personOwnerId: Long = 0,
    var amount: Long = 0
)