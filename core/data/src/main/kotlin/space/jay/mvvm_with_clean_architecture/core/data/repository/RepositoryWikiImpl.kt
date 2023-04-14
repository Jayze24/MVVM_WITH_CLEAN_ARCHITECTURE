package space.jay.mvvm_with_clean_architecture.core.data.repository

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryWiki
import space.jay.mvvm_with_clean_architecture.core.model.wiki.EntityWiki
import space.jay.mvvm_with_clean_architecture.core.network.SourceWiki
import javax.inject.Inject

class RepositoryWikiImpl @Inject constructor(
    private val sourceWiki : SourceWiki,
) : RepositoryWiki {

    override suspend fun getListWikiRelated(query : String) : Result<List<EntityWiki>> {
        return sourceWiki.getListWikiRelated(query)
    }

    override suspend fun getWikiSummary(query : String) : Result<EntityWiki> {
        return sourceWiki.getWikiSummary(query)
    }

}