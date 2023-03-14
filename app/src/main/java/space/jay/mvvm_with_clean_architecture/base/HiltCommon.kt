package space.jay.mvvm_with_clean_architecture.base

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

@Module
@InstallIn(SingletonComponent::class)
object HiltCommon {
    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}