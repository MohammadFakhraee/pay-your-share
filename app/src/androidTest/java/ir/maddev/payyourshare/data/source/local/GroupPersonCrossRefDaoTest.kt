package ir.maddev.payyourshare.data.source.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class GroupPersonCrossRefDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var groupPersonCrossRefDao: GroupPersonCrossRefDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        applicationDatabase.run {
            personDao().saveAll(testPersons)
            groupDao().saveAll(testGroups)
            groupPersonCrossRefDao = groupPersonDao().also { it.saveAll(testGroupPersons) }
        }
    }

    @After
    fun shutdown() {
        applicationDatabase.close()

    }

    @Test
    fun getAll() = runTest {
        assertThat(groupPersonCrossRefDao.getAll()).containsExactlyElementsIn(testGroupPersons)
    }

    @Test
    fun delete() = runTest {
        groupPersonCrossRefDao.delete(testGroupPersons[0])
        assertThat(groupPersonCrossRefDao.getAll()).containsExactly(testGroupPersons[1], testGroupPersons[2])
    }

    @Test
    fun deleteByGroupId() = runTest {
        groupPersonCrossRefDao.deleteByGroupId(testGroup.id)
        assertThat(groupPersonCrossRefDao.getAll()).isEmpty()
    }

    @Test
    fun deleteByPersonId() = runTest {
        groupPersonCrossRefDao.deleteByPersonId(testPerson.id)
        val allGroupPersons = groupPersonCrossRefDao.getAll()
        assertThat(allGroupPersons).containsExactlyElementsIn(testGroupPersons.filter { it.personId != testPerson.id })
    }
}