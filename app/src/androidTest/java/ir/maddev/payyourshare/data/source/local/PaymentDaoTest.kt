package ir.maddev.payyourshare.data.source.local

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.data.model.local.PaymentLocal
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

    @Before
    fun setup() {
        hiltRule.inject()
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
}