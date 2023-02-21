package ir.maddev.payyourshare.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShareLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "shareId") var id: Long = 0,
    var paymentOwnerId: Long = 0,
    var personOwnerId: Long = 0,
    var amount: Long = 0
)