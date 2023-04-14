package space.jay.mvvm_with_clean_architecture.core.domain

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.mvvm_with_clean_architecture.core.common.di.DispatcherIO
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryWiki
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetWikiData @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val repository : RepositoryWiki,
) {

    suspend operator fun invoke(query : String) = withContext(dispatcher) {
        return@withContext repository.getWikiSummary(query)
    }

}