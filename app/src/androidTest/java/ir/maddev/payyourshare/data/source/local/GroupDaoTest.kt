package ir.maddev.payyourshare.data.source.local

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.maddev.payyourshare.core.BaseDaoTest
import ir.maddev.payyourshare.data.model.local.GroupLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class GroupDaoTest : BaseDaoTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun shutdown() {
        applicationDatabase.close()
    }

    @Test
    fun insertGroupLocal() = runTest {
        val groupLocal = GroupLocal(id = 1)
        groupDao.save(groupLocal)

        val allGroups = groupDao.getAll()
        assertThat(allGroups[0]).isEqualTo(groupLocal)
    }
}