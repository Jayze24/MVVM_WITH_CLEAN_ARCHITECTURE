package space.jay.mvvm_with_clean_architecture._core.data.repository

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.NetworkResult
import space.jay.mvvm_with_clean_architecture._core.data.RepositoryWiki
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki
import space.jay.mvvm_with_clean_architecture._core.network.SourceWiki
import javax.inject.Inject

class RepositoryWikiImpl @Inject constructor(
    private val sourceWiki : SourceWiki,
) : RepositoryWiki {

    override suspend fun getListWikiRelated(query : String) : NetworkResult<List<EntityWiki>> {
        return sourceWiki.getListWikiRelated(query)
    }

    override suspend fun getWikiSummary(query : String) : NetworkResult<EntityWiki> {
        return sourceWiki.getWikiSummary(query)
    }

}