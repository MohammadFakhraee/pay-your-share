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
class PaymentDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var paymentDao: PaymentDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()

        applicationDatabase.run {
            personDao().saveAll(testPersons)
            groupDao().saveAll(testGroups)
            groupPersonDao().saveAll(testGroupPersons)
            paymentDao().saveAll(testPayments)
            shareDao().saveAll(testShareAll)
            tagDao().saveAll(testTags)
            paymentTagDao().saveAll(testPaymentTags)

            paymentDao = paymentDao()
        }
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun insertPayment() = runTest {
        assertThat(paymentDao.getAll()).containsExactlyElementsIn(testPayments)
    }

    @Test
    fun delete() = runTest {
        paymentDao.delete(testPayment)
        assertThat(paymentDao.getAll()).containsExactlyElementsIn(testPayments.filter { it.id != testPayment.id })
    }

    @Test
    fun deleteById() = runTest {
        paymentDao.deleteById(testPayment.id)
        assertThat(paymentDao.getAll()).containsExactlyElementsIn(testPayments.filter { it.id != testPayment.id })
    }

    @Test
    fun getAllPaymentsWithSharesAndTags() = runTest {
        val allPaymentsWithShares = paymentDao.getAllPaymentsWithSharesAndTags()
        assertThat(allPaymentsWithShares).isEqualTo(testAllPaymentWithSharesAndTags)
    }
}