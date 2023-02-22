package ir.maddev.payyourshare.data.model.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ir.maddev.payyourshare.data.model.local.GroupLocal
import ir.maddev.payyourshare.data.model.local.PersonLocal
import ir.maddev.payyourshare.data.model.local.crossrefs.GroupPersonCrossRef

data class GroupWithPersons(
    @Embedded val group: GroupLocal, @Relation(
        parentColumn = "group_id", entityColumn = "person_id", associateBy = Junction(GroupPersonCrossRef::class)
    ) val people: List<PersonLocal>
)