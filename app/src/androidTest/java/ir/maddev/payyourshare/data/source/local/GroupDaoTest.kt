package ir.maddev.payyourshare.data.source.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class GroupDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase
    lateinit var groupDao: GroupDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        applicationDatabase.run {
            groupDao().saveAll(testGroups)
            personDao().saveAll(testPersons)
            groupPersonDao().saveAll(testGroupPersons)
            paymentDao().saveAll(testPayments)
            shareDao().saveAll(testShareAll)
            tagDao().saveAll(testTags)
            paymentTagDao().saveAll(testPaymentTags)

            groupDao = groupDao()
        }
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun getAll() = runTest {
        assertThat(groupDao.getAll()).containsExactlyElementsIn(testGroups)
    }

    @Test
    fun getAllStream() = runTest {
        assertThat(groupDao.getAllStream().first()).containsExactlyElementsIn(testGroups)
    }

    @Test
    fun delete() = runTest {
        groupDao.delete(testGroup)
        assertThat(groupDao.getAll()).containsExactlyElementsIn(testGroups.filter { it.id != testGroup.id })
    }

    @Test
    fun deleteById() = runTest {
        groupDao.deleteById(testGroup.id)
        assertThat(groupDao.getAll()).containsExactlyElementsIn(testGroups.filter { it.id != testGroup.id })
    }

    @Test
    fun getAllGroupsWithPersons() = runTest {
        val allGroupsWithPersons = groupDao.getAllGroupsWithPersons()
        assertThat(allGroupsWithPersons).isEqualTo(testAllGroupsWithPersons)
    }

    @Test
    fun getAllGroupsWithPersonsStream() = runTest {
        val allGroupsWithPersons = groupDao.getAllGroupsWithPersonsStream().first()
        assertThat(allGroupsWithPersons).isEqualTo(testAllGroupsWithPersons)
    }
}