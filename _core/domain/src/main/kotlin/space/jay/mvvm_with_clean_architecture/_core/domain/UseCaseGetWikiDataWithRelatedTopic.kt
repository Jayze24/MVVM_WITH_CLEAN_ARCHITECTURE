package space.jay.mvvm_with_clean_architecture._core.domain

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.withContext
import space.jay.mvvm_with_clean_architecture._core.common.di.DispatcherIO
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture._core.data.RepositoryWiki
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetWikiDataWithRelatedTopic @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val repository : RepositoryWiki,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(query : String) = withContext(dispatcher) {
        val wiki = async { repository.getWikiSummary(query) }
        val related = async { repository.getListWikiRelated(query) }
        joinAll(wiki, related)

        val result = wiki.getCompleted()
        if (result is Success) {
            val resultRelated = related.getCompleted()
            if (resultRelated is Success) {
                return@withContext Success(result.data.copy(listRelated = resultRelated.data))
            }
        }
        return@withContext result
    }

}