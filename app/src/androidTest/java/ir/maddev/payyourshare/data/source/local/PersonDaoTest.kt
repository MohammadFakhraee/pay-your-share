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
class PersonDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var personDao: PersonDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        personDao = applicationDatabase.personDao()
        personDao.saveAll(testPersons)
        applicationDatabase.paymentDao().saveAll(testPayments)
        applicationDatabase.shareDao().saveAll(testShareAll)
        applicationDatabase.tagDao().saveAll(testTags)
        applicationDatabase.paymentTagDao().saveAll(testPaymentTags)
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun getAll() = runTest {
        assertThat(personDao.getAll()).containsExactlyElementsIn(testPersons)
    }

    @Test
    fun getAllStream() = runTest {
        assertThat(personDao.getAllStream().first()).containsExactlyElementsIn(testPersons)
    }

    @Test
    fun delete() = runTest {
        personDao.delete(testPerson)
        assertThat(personDao.getAll()).containsExactly(testPersons[1], testPersons[2])
    }

    @Test
    fun deleteById() = runTest {
        personDao.deleteById(testPerson.id)
        assertThat(personDao.getAll()).containsExactly(testPersons[1], testPersons[2])
    }

    @Test
    fun getAllPersonsWithPaymentsWithSharesAndTags() = runTest {
        val allPersonWithPayments = personDao.getAllPersonsWithPaymentsWithSharesAndTags()
        assertThat(allPersonWithPayments.size).isEqualTo(testPersons.size)

        val personWithPayment = allPersonWithPayments[0]
        assertThat(personWithPayment.personLocal).isEqualTo(testPerson)
        assertThat(personWithPayment.paymentsWithSharesAndTags.size).isEqualTo(testPayments.size)

        val paymentWithSharesAndTags = personWithPayment.paymentsWithSharesAndTags[0]
        assertThat(paymentWithSharesAndTags.paymentLocal).isEqualTo(testPayment)
        assertThat(paymentWithSharesAndTags.tags).containsExactlyElementsIn(testTags)
        assertThat(paymentWithSharesAndTags.shareLocals).containsExactlyElementsIn(testShares1)
    }

    @Test
    fun getAllPersonsWithPaymentsWithSharesAndTagsStream() = runTest {
        val allPersonWithPayments = personDao.getAllPersonsWithPaymentsWithSharesAndTagsStream().first()
        assertThat(allPersonWithPayments.size).isEqualTo(testPersons.size)

        val personWithPayment = allPersonWithPayments[0]
        assertThat(personWithPayment.personLocal).isEqualTo(testPerson)
        assertThat(personWithPayment.paymentsWithSharesAndTags.size).isEqualTo(testPayments.size)

        val paymentWithSharesAndTags = personWithPayment.paymentsWithSharesAndTags[0]
        assertThat(paymentWithSharesAndTags.paymentLocal).isEqualTo(testPayment)
        assertThat(paymentWithSharesAndTags.tags).containsExactlyElementsIn(testTags)
        assertThat(paymentWithSharesAndTags.shareLocals).containsExactlyElementsIn(testShares1)
    }

    @Test
    fun getPersonWithPaymentsWithSharesAndTagsById() = runTest {
        val personWithPayment = personDao.getPersonWithPaymentsWithSharesAndTagsById(testPerson.id)
        assertThat(personWithPayment.personLocal).isEqualTo(testPerson)
        assertThat(personWithPayment.paymentsWithSharesAndTags.size).isEqualTo(testPayments.size)

        val paymentWithSharesAndTags = personWithPayment.paymentsWithSharesAndTags[0]
        assertThat(paymentWithSharesAndTags.paymentLocal).isEqualTo(testPayment)
        assertThat(paymentWithSharesAndTags.tags).containsExactlyElementsIn(testTags)
        assertThat(paymentWithSharesAndTags.shareLocals).containsExactlyElementsIn(testShares1)
    }

    @Test
    fun getPersonWithPaymentsWithSharesAndTagsByIdStream() = runTest {
        val personWithPayment = personDao.getPersonWithPaymentsWithSharesAndTagsByIdStream(testPerson.id).first()
        assertThat(personWithPayment.personLocal).isEqualTo(testPerson)
        assertThat(personWithPayment.paymentsWithSharesAndTags.size).isEqualTo(testPayments.size)

        val paymentWithSharesAndTags = personWithPayment.paymentsWithSharesAndTags[0]
        assertThat(paymentWithSharesAndTags.paymentLocal).isEqualTo(testPayment)
        assertThat(paymentWithSharesAndTags.tags).containsExactlyElementsIn(testTags)
        assertThat(paymentWithSharesAndTags.shareLocals).containsExactlyElementsIn(testShares1)
    }
}