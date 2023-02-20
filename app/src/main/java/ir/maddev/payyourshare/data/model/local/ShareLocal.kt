package ir.maddev.payyourshare.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.PrimaryKey

@Entity(
    tableName = "tbl_shares",
    foreignKeys = [
        ForeignKey(
            entity = PaymentLocal::class,
            parentColumns = ["paymentId"],
            childColumns = ["paymentOwnerId"],
            onDelete = NO_ACTION,
            onUpdate = CASCADE
        ),
        ForeignKey(
            entity = PersonLocal::class,
            parentColumns = ["personId"],
            childColumns = ["personOwnerId"],
            onDelete = NO_ACTION,
            onUpdate = CASCADE
        )
    ]
)
data class ShareLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "shareId") var id: Long = 0,
    var paymentOwnerId: Long = 0,
    var personOwnerId: Long = 0,
    var amount: Long = 0
)