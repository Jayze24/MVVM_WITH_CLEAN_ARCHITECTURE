package space.jay.mvvm_with_clean_architecture.core.domain

import android.content.Context
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.qualifiers.ApplicationContext
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
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryPokemonGo
import space.jay.mvvm_with_clean_architecture.core.domain.fake.FakeRepositoryPokemonGo
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class TestUseCaseGetListPokemon {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject @ApplicationContext lateinit var context : Context
    @Inject @DispatcherIO lateinit var dispatcher : TestDispatcher
    lateinit var repository : RepositoryPokemonGo

    @Before
    fun setUp() = runTest {
        hiltRule.inject()
        repository = FakeRepositoryPokemonGo(context)
    }

    @Test
    fun useCaseGetListPokemon_success() = runTest {
        val useCaseGetListPokemon = UseCaseGetListPokemon(dispatcher, repository).invoke()
        assertThat(useCaseGetListPokemon is Success).isTrue()
    }
}