package space.jay.mvvm_with_clean_architecture._core.network

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki

interface SourceWiki {

    suspend fun getListWikiRelated(query : String) : Result<List<EntityWiki>>

    suspend fun getWikiSummary(query : String) : Result<EntityWiki>

}