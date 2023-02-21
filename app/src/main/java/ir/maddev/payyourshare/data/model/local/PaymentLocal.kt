package ir.maddev.payyourshare.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.PrimaryKey

/**
 * Every [PersonLocal] can make a payment in a [GroupLocal].
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GroupLocal::class,
            parentColumns = ["groupId"],
            childColumns = ["groupOwnerId"],
            onDelete = CASCADE,
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
data class PaymentLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "paymentId") var id: Long = 0,
    var groupOwnerId: Long = 0,
    var personOwnerId: Long = 0,
    var title: String = "",
    var description: String = "",
    var totalAmount: Long = 0
)
