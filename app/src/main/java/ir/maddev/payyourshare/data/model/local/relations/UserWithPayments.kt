package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.PersonLocal

data class UserWithPayments(
    @Embedded val personLocal: PersonLocal,
    @Relation(
        parentColumn = "personId",
        entityColumn = ""
    )
)