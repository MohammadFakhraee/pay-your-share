package ir.maddev.payyourshare.data.model.local.relations

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TagLocal(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "tag_id") var id: Long = 0,
    var title: String = "",
    var color: Int = Color.BLACK,
    var iconPath: String = ""
)