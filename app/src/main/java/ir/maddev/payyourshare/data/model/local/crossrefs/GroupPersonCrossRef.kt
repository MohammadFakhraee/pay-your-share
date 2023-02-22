package ir.maddev.payyourshare.data.model.local.crossrefs

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["group_id", "person_id"])
data class GroupPersonCrossRef(
    @ColumnInfo(name = "group_id") var groupId: Long = 0,
    @ColumnInfo(name = "person_id") var personId: Long = 0
)