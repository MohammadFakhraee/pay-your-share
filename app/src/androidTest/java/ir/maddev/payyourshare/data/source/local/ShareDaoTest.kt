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

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
@HiltAndroidTest
class ShareDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var shareDao: ShareDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()

        applicationDatabase.run {
            personDao().saveAll(testPersons)
            groupDao().saveAll(testGroups)
            groupPersonDao().saveAll(testGroupPersons)
            paymentDao().saveAll(testPayments)
            shareDao().saveAll(testShareAll)

            shareDao = shareDao()
        }
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun getAll() = runTest {
        assertThat(shareDao.getAll()).containsExactlyElementsIn(testShareAll)
    }

    @Test
    fun delete() = runTest {
        shareDao.delete(testShares1[0])
        assertThat(shareDao.getAll()).containsExactlyElementsIn(testShareAll.filter { it.id != testShares1[0].id })
    }

    @Test
    fun deleteById() = runTest {
        shareDao.deleteById(testShares1[0].id)
        assertThat(shareDao.getAll()).containsExactlyElementsIn(testShareAll.filter { it.id != testShares1[0].id })
    }
}