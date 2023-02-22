package ir.maddev.payyourshare.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A Group to create a collection of people for specific purpose
 */
@Entity(tableName = "groups")
data class GroupLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "group_id") var id: Long = 0,
    var name: String = "",
    var purpose: String = "",
    var color: String = ""
) {
    companion object {
        fun createEmptyInstance() = GroupLocal()
    }
}
