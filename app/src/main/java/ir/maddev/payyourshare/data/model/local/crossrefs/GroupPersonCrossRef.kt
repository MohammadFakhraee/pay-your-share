package ir.maddev.payyourshare.data.model.local.crossrefs

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupPersonCrossRef(
    @PrimaryKey val groupId: Long = 0,
    @PrimaryKey val personId: Long = 0
)