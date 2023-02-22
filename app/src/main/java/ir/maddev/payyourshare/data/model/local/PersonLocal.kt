package ir.maddev.payyourshare.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Person who can be added to a [GroupLocal] as a member
 */
@Entity(tableName = "persons")
data class PersonLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "person_id") var id: Long = 0,
    var name: String = "",
    var avatarPath: String = ""
)
