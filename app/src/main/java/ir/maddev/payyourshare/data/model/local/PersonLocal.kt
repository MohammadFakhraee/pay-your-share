package ir.maddev.payyourshare.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Person who can be added to a [GroupLocal] as a member
 */
@Entity
data class PersonLocal(
    @PrimaryKey(autoGenerate = true) var personId: Long = 0,
    var name: String = "",
    var avatarPath: String = ""
)
