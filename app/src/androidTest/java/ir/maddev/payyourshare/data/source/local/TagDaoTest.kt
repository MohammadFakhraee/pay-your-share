package ir.maddev.payyourshare.data.source.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.utils.testTags
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
class TagDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var applicationDatabase: ApplicationDatabase
    private lateinit var tagDao: TagDao

    @Before
    fun setup() = runTest {
        hiltRule.inject()
        tagDao = applicationDatabase.tagDao()
        tagDao.saveAll(testTags)
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun saveTags() = runTest {
        assertThat(tagDao.getAll()).containsExactlyElementsIn(testTags)
    }

    @Test
    fun deleteTag() = runTest {
        tagDao.delete(testTags[0])
        assertThat(tagDao.getAll()).containsExactlyElementsIn(testTags.apply { remove(testTags[0]) })
    }

    @Test
    fun deleteTagById() = runTest {
        tagDao.deleteById(testTags[0].id)
        assertThat(tagDao.getAll()).containsExactlyElementsIn(testTags.apply { remove(testTags[0]) })
    }

    @Test
    fun getAllStream() = runTest {
        assertThat(tagDao.getAllStream().first()).containsExactlyElementsIn(testTags)
    }
}