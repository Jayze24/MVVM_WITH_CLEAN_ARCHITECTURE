package space.jay.mvvm_with_clean_architecture.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.mvvm_with_clean_architecture.domain.boundary.BoundaryWiki

class UseCaseGetWikiSummary(
    private val dispatcher : CoroutineDispatcher,
    private val repository : BoundaryWiki,
) {

    suspend operator fun invoke(query : String) = withContext(dispatcher) {
        return@withContext repository.getWikiSummary(query)
    }
}