package ir.maddev.payyourshare.data.model.local.crossrefs

import androidx.room.Entity

@Entity(primaryKeys = ["groupId", "personId"])
data class GroupPersonCrossRef(
    var groupId: Long = 0,
    var personId: Long = 0
)