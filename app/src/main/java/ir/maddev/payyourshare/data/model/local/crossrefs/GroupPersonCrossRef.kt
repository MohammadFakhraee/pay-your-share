package ir.maddev.payyourshare.data.model.local.crossrefs

import androidx.room.Entity

@Entity(primaryKeys = ["groupId", "personId"])
data class GroupPersonCrossRef(
    val groupId: Long,
    val personId: Long
)