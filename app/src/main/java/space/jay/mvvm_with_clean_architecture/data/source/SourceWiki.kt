package space.jay.mvvm_with_clean_architecture.data.source

import space.jay.mvvm_with_clean_architecture.domain.entity.EntityWiki

interface SourceWiki {

    suspend fun getListWikiRelated(query : String) : List<EntityWiki>?

    suspend fun getWikiSummary(query : String) : EntityWiki?

}