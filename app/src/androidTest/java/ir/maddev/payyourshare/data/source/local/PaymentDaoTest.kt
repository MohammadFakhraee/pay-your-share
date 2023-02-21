package ir.maddev.payyourshare.data.source.local

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.data.model.local.PaymentLocal
import ir.maddev.payyourshare.data.model.local.ShareLocal
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
class PaymentDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase

    @Inject
    @Named("payment_dao")
    lateinit var paymentDao: PaymentDao

    @Inject
    @Named("share_dao")
    lateinit var shareDao: ShareDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun shutdown() {
//        applicationDatabase.close()
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
    fun insertPaymentWithShare() = runTest {
        val payment = PaymentLocal(id = 1, totalAmount = 100L)
        val share1 = ShareLocal(id = 1, paymentOwnerId = 1, amount = 45L)
        val share2 = ShareLocal(id = 2, paymentOwnerId = 1, amount = 55L)

        paymentDao.save(payment)
        shareDao.save(share1)
        shareDao.save(share2)

        val paymentWithShares = paymentDao.getAll()[0]
        assertThat(paymentWithShares.paymentLocal).isEqualTo(payment)
        assertThat(paymentWithShares.shareLocal).contains(share1)
        assertThat(paymentWithShares.shareLocal).contains(share2)
    }
}