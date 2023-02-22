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

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
@HiltAndroidTest
class ShareDaoTest {

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
        applicationDatabase.close()
    }

    @Test
    fun insertShareLocal() = runTest {
        val paymentLocal = PaymentLocal(id = 1, totalAmount = 250)
        paymentDao.save(paymentLocal)
        val shareLocal = ShareLocal(id = 1, paymentOwnerId = 1, amount = 250L)
        shareDao.save(shareLocal)

        val allShareLocal = shareDao.getAll()
        assertThat(allShareLocal[0]).isEqualTo(shareLocal)
    }

    @Test
    fun removeShareLocal() = runTest {
        val shareLocal = ShareLocal(id = 1)
        shareDao.save(shareLocal)
        shareDao.delete(shareLocal)

        val allShareLocal = shareDao.getAll()
        assertThat(allShareLocal).isEmpty()
    }
}