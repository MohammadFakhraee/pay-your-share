package ir.maddev.payyourshare.data.source.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.ShareLocal
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

    @Inject
    @Named("payment_dao")
    lateinit var paymentDao: PaymentDao

    @Inject
    @Named("share_dao")
    lateinit var shareDao: ShareDao

    private val paymentLocal = PaymentLocal(id = 1, totalAmount = 100)
    private val shareLocal1 = ShareLocal(id = 1, paymentOwnerId = 1, amount = 45)
    private val shareLocal2 = ShareLocal(id = 2, paymentOwnerId = 1, amount = 55)

    @Before
    fun setup(): Unit = runBlocking {
        hiltRule.inject()
        paymentDao.save(paymentLocal)
        shareDao.save(shareLocal1)
        shareDao.save(shareLocal2)
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun insertPaymentWithoutShare() = runTest {
        val paymentLocal = PaymentLocal(id = 1)
        paymentDao.save(paymentLocal)

        val allPayments = paymentDao.getAll()
        assertThat(allPayments[0].paymentLocal).isEqualTo(paymentLocal)
    }

    @Test
    fun removePaymentWithoutShare() = runTest {
        val paymentLocal = PaymentLocal(id = 1)
        paymentDao.save(paymentLocal)
        paymentDao.delete(paymentLocal)

        val allPayments = paymentDao.getAll()
        assertThat(allPayments).isEmpty()
    }

    @Test
    fun insertPaymentWithShares() = runBlocking {
        val paymentWithShares = paymentDao.getAll()[0]
        assertThat(paymentWithShares.paymentLocal).isEqualTo(paymentLocal)
        val shareLocals = paymentWithShares.shareLocals
        assertThat(shareLocals).contains(shareLocal1)
        assertThat(shareLocals).contains(shareLocal2)
        assertThat(shareLocals.sumOf { it.amount }).isEqualTo(paymentLocal.totalAmount)
    }
}