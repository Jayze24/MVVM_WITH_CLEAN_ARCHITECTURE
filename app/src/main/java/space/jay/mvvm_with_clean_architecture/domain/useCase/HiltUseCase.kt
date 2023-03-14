package space.jay.mvvm_with_clean_architecture.domain.useCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import space.jay.mvvm_with_clean_architecture.base.DispatcherIO
import space.jay.mvvm_with_clean_architecture.domain.boundary.BoundaryWiki

@InstallIn(ViewModelComponent::class)
@Module
object HiltUseCase {

    @Provides
    fun provideUseCaseGetListWikiRelated(
        @DispatcherIO dispatcher : CoroutineDispatcher,
        repository : BoundaryWiki,
    ) = UseCaseGetListWikiRelated(dispatcher = dispatcher, repository = repository)

    @Provides
    fun provideUseCaseGetWikiSummary(
        @DispatcherIO dispatcher : CoroutineDispatcher,
        repository : BoundaryWiki,
    ) = UseCaseGetWikiSummary(dispatcher = dispatcher, repository = repository)
}