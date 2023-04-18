package space.jay.mvvm_with_clean_architecture.core.domain

import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import space.jay.mvvm_with_clean_architecture.core.common.di.DispatcherIO
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.NetworkError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.domain.fake.FakeRepositoryWiki
import space.jay.mvvm_with_clean_architecture.core.model.wiki.EntityWiki
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class TestUseCaseGetWikiDataWithRelatedTopic {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @DispatcherIO
    lateinit var dispatcher : TestDispatcher

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun useCaseGetWikiDataWithRelatedTopic_has_wiki_and_Related_data() = runTest {
        val fakeRepositoryWiki = FakeRepositoryWiki(
            fakeWiki = EntityWiki(),
            fakeListRelated = listOf(EntityWiki(), EntityWiki(), EntityWiki())
        )
        val useCaseGetWikiDataWithRelatedTopic = UseCaseGetWikiDataWithRelatedTopic(dispatcher, fakeRepositoryWiki).invoke("test")
        assertThat(useCaseGetWikiDataWithRelatedTopic is Success).isTrue()
        if (useCaseGetWikiDataWithRelatedTopic is Success) {
            assertThat(useCaseGetWikiDataWithRelatedTopic.data).isNotNull()
            assertThat(useCaseGetWikiDataWithRelatedTopic.data.listRelated).isNotNull()
        }
    }

    @Test
    fun useCaseGetWikiDataWithRelatedTopic_has_wiki_and_no_Related_data() = runTest {
        val fakeRepositoryWiki = FakeRepositoryWiki(
            fakeWiki = EntityWiki(),
            fakeListRelated = null
        )
        val useCaseGetWikiDataWithRelatedTopic = UseCaseGetWikiDataWithRelatedTopic(dispatcher, fakeRepositoryWiki).invoke("test")
        assertThat(useCaseGetWikiDataWithRelatedTopic is Success).isTrue()
        if (useCaseGetWikiDataWithRelatedTopic is Success) {
            assertThat(useCaseGetWikiDataWithRelatedTopic.data).isNotNull()
            assertThat(useCaseGetWikiDataWithRelatedTopic.data.listRelated).isNull()
        }
    }

    @Test
    fun useCaseGetWikiDataWithRelatedTopic_no_wiki_and_has_Related_data() = runTest {
        val fakeRepositoryWiki = FakeRepositoryWiki(
            fakeWiki = null,
            fakeListRelated = listOf(EntityWiki(), EntityWiki(), EntityWiki())
        )
        val useCaseGetWikiDataWithRelatedTopic = UseCaseGetWikiDataWithRelatedTopic(dispatcher, fakeRepositoryWiki).invoke("test")
        assertThat(useCaseGetWikiDataWithRelatedTopic is NetworkError).isTrue()
        if (useCaseGetWikiDataWithRelatedTopic is NetworkError) {
            assertThat(useCaseGetWikiDataWithRelatedTopic.code).isIn(0 until 600)
        }
    }
}