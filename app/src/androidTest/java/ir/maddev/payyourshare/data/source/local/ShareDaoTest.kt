package ir.maddev.payyourshare.data.source.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.utils.testPayment
import ir.maddev.payyourshare.utils.testPersons
import ir.maddev.payyourshare.utils.testShares1
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
        applicationDatabase.personDao().saveAll(testPersons)
        applicationDatabase.paymentDao().save(testPayment)
        shareDao = applicationDatabase.shareDao().also { it.saveAll(testShares1) }
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun insertShareLocal() = runTest {
        assertThat(shareDao.getAll()).containsExactlyElementsIn(testShares1)
    }

    @Test
    fun deleteShareLocale() = runTest {
        shareDao.delete(testShares1[0])
        assertThat(shareDao.getAll()).containsExactly(testShares1[1], testShares1[2])
    }

    @Test
    fun deleteShareLocaleById() = runTest {
        shareDao.deleteById(testShares1[0].id)
        assertThat(shareDao.getAll()).containsExactly(testShares1[1], testShares1[2])
    }
}