package ir.maddev.payyourshare.data.repo.reference

import ir.maddev.payyourshare.data.model.local.crossrefs.GroupPersonCrossRef

interface GroupPersonCrossRefRepository {

    suspend fun save(groupPersonCrossRef: GroupPersonCrossRef)

    suspend fun saveAll(groupPersons: List<GroupPersonCrossRef>)

    suspend fun delete(groupPersonCrossRef: GroupPersonCrossRef)

    suspend fun deleteByGroupId(groupId: Long)

    suspend fun deleteByPersonId(personId: Long)

    suspend fun getAll(): List<GroupPersonCrossRef>
}