package ir.maddev.payyourshare.data.model.local.crossrefs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "group_person",
    primaryKeys = ["group_id", "person_id"],
    indices = [
        Index(
            value = arrayOf("person_id")
        )
    ]
)
data class GroupPersonCrossRef(
    @ColumnInfo(name = "group_id") var groupId: Long = 0,
    @ColumnInfo(name = "person_id") var personId: Long = 0
)