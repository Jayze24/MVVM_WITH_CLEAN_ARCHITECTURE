package space.jay.mvvm_with_clean_architecture.domain.boundary

import space.jay.mvvm_with_clean_architecture.domain.entity.EntityWiki

interface BoundaryWiki {

    suspend fun getListWikiRelated(query: String): List<EntityWiki>?
    suspend fun getWikiSummary(query: String): EntityWiki?

}