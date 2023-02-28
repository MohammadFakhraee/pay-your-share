package ir.maddev.payyourshare.data.model.local

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * Every [PersonLocal] can make a payment in a [GroupLocal].
 * This class holds each payment instances
 */
@Entity(
    tableName = "payments", foreignKeys = [ForeignKey(
        entity = PersonLocal::class, parentColumns = ["person_id"], childColumns = ["person_owner_id"], onDelete = CASCADE, onUpdate = CASCADE
    )],
    indices = [Index("person_owner_id")]
)
data class PaymentLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "payment_id") var id: Long = 0,
    @ColumnInfo(name = "group_owner_id") var groupOwnerId: Long = 0,
    @ColumnInfo(name = "person_owner_id") var personOwnerId: Long = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "total_amount") var totalAmount: Long = 0,
    @ColumnInfo(name = "pay_time") var payTime: Long = System.currentTimeMillis()
)
