package space.jay.mvvm_with_clean_architecture.core.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatcherIO

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DispatcherDefault

@Module
@InstallIn(SingletonComponent::class)
object HiltCommon {
    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DispatcherDefault
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default
}