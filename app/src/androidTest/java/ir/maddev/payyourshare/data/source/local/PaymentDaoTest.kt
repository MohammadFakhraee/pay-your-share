package ir.maddev.payyourshare.data.source.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.utils.testPayments
import ir.maddev.payyourshare.utils.testShares1
import ir.maddev.payyourshare.utils.testShares2
import ir.maddev.payyourshare.utils.testShares3
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
class PaymentDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase

    lateinit var paymentDao: PaymentDao
    lateinit var shareDao: ShareDao

    @Before
    fun setup(): Unit = runBlocking {
        hiltRule.inject()
        paymentDao = applicationDatabase.paymentDao()
        shareDao = applicationDatabase.shareDao()

        paymentDao.saveAll(testPayments)
        shareDao.saveAll(testShares1 + testShares2 + testShares3)

    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun insertPayment() = runTest {
        assertThat(paymentDao.getAll().size).isEqualTo(3)
    }

    @Test
    fun deletePayment() = runTest {
        paymentDao.delete(testPayments[0])
        assertThat(paymentDao.getAll().size).isEqualTo(2)
    }

    @Test
    fun deleteById() = runTest {
        paymentDao.deleteById(testPayments[0].id)
        assertThat(paymentDao.getAll().size).isEqualTo(2)
    }

    @Test
    fun getPaymentWithShares() = runTest {
        val paymentWithShares = paymentDao.getAll()[0]
        assertThat(paymentWithShares.paymentLocal).isEqualTo(testPayments[0])
        val shareLocals = paymentWithShares.shareLocals
        assertThat(shareLocals).containsExactlyElementsIn(testShares1)
    }
}