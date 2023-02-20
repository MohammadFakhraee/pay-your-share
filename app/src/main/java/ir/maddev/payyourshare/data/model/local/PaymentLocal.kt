package ir.maddev.payyourshare.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Every [PersonLocal] can make a payment in a [GroupLocal].
 */
@Entity
data class PaymentLocal(
    @PrimaryKey(autoGenerate = true) var paymentId: Long = 0,
    var groupOwnerId: Long = 0,
    var personOwnerId: Long = 0,
    var title: String = "",
    var description: String = "",
    var totalAmount: Long = 0
)
