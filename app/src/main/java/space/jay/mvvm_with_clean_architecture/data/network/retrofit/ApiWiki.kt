package space.jay.mvvm_with_clean_architecture.data.network.retrofit

import space.jay.mvvm_with_clean_architecture.data.network.retrofit.data.asEntity
import space.jay.mvvm_with_clean_architecture.data.network.retrofit.service.ServiceWiki
import space.jay.mvvm_with_clean_architecture.data.source.SourceWiki
import space.jay.mvvm_with_clean_architecture.domain.entity.EntityWiki
import javax.inject.Inject

class ApiWiki @Inject constructor(
    private val serviceWiki : ServiceWiki
) : SourceWiki {

    override suspend fun getListWikiRelated(query : String) : List<EntityWiki>? {
        val result = serviceWiki.getListWikiRelated(query)
        return if (result.isSuccessful && result.body() != null) {
            result.body()!!.pages?.map { it.asEntity() }
        } else {
            null
        }
    }

    override suspend fun getWikiSummary(query : String) : EntityWiki? {
        val result = serviceWiki.getWikiSummary(query)
        return if (result.isSuccessful && result.body() != null) {
            result.body()?.asEntity()
        } else {
            null
        }
    }
}