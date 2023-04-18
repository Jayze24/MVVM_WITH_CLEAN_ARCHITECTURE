package space.jay.mvvm_with_clean_architecture.core.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import space.jay.mvvm_with_clean_architecture.core.common.di.DispatcherIO
import space.jay.mvvm_with_clean_architecture.core.common.di.HiltCommon

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [HiltCommon::class],
)
object TestHiltCommon {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): TestDispatcher = UnconfinedTestDispatcher()
}