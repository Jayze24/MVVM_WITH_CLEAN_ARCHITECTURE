package space.jay.mvvm_with_clean_architecture.data

import space.jay.mvvm_with_clean_architecture.data.source.SourceWiki
import space.jay.mvvm_with_clean_architecture.domain.boundary.BoundaryWiki
import space.jay.mvvm_with_clean_architecture.domain.entity.EntityWiki
import javax.inject.Inject

class RepositoryWiki @Inject constructor(
    private val sourceWiki : SourceWiki,
) : BoundaryWiki {

    override suspend fun getListWikiRelated(query : String) : List<EntityWiki>? {
        return sourceWiki.getListWikiRelated(query = query)
    }

    override suspend fun getWikiSummary(query : String) : EntityWiki? {
        return sourceWiki.getWikiSummary(query = query)
    }

}