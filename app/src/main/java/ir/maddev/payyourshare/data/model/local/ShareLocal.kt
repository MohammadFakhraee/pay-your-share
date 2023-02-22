package ir.maddev.payyourshare.data.model.local

import androidx.room.*

@Entity(
    tableName = "shares",
    foreignKeys = [
        ForeignKey(entity = PaymentLocal::class, parentColumns = ["payment_id"], childColumns = ["payment_owner_id"])
    ],
    indices = [Index("payment_owner_id")]
)
data class ShareLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "shareId") var id: Long = 0,
    @ColumnInfo(name = "payment_owner_id") var paymentOwnerId: Long = 0,
    @ColumnInfo(name = "person_owner_id") var personOwnerId: Long = 0,
    var amount: Long = 0
)