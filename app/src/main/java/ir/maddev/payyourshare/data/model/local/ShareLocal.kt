package ir.maddev.payyourshare.data.model.local

import androidx.room.PrimaryKey

data class ShareLocal(
    @PrimaryKey(autoGenerate = true) var shareId: Long = 0,
    var paymentOwnerId: Long = 0,
    var personOwnerId: Long = 0,
    var amount: Long = 0
)