package ir.maddev.payyourshare.data.source.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.utils.testPayment
import ir.maddev.payyourshare.utils.testPaymentTags
import ir.maddev.payyourshare.utils.testPersons
import ir.maddev.payyourshare.utils.testTags
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
class PaymentTagCrossRefDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase
    lateinit var paymentTagCrossRefDao: PaymentTagCrossRefDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        applicationDatabase.run {
            personDao().saveAll(testPersons)
            paymentDao().save(testPayment)
            tagDao().saveAll(testTags)
        }
        paymentTagCrossRefDao = applicationDatabase.paymentTagDao().also { it.saveAll(testPaymentTags) }
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun insertPaymentTagCrossRef() = runTest {
        assertThat(paymentTagCrossRefDao.getAll()).containsExactlyElementsIn(testPaymentTags)
    }

    @Test
    fun deletePaymentTagsByPaymentId() = runTest {
        paymentTagCrossRefDao.deleteByPaymentId(testPayment.id)
        assertThat(paymentTagCrossRefDao.getAll()).isEmpty()
    }
}