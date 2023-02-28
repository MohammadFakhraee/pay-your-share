package ir.maddev.payyourshare.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Every [PersonLocal] can make a payment in a [GroupLocal].
 */
@Entity(tableName = "payments")
data class PaymentLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "payment_id") var id: Long = 0,
    @ColumnInfo(name = "group_owner_id") var groupOwnerId: Long = 0,
    @ColumnInfo(name = "person_owner_id") var personOwnerId: Long = 0,
    var title: String = "",
    var description: String = "",
    var totalAmount: Long = 0
)
