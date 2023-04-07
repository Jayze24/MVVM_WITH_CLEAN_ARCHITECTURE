package space.jay.mvvm_with_clean_architecture._core.network.retrofit

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki
import space.jay.mvvm_with_clean_architecture._core.network.SourceWiki
import space.jay.mvvm_with_clean_architecture._core.network.model.asEntity
import space.jay.mvvm_with_clean_architecture._core.network.retrofit.dao.DaoWiki
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetworkWiki @Inject internal constructor(
    private val networkApi : DaoWiki
) : BaseRetrofitNetwork(), SourceWiki {

    override suspend fun getListWikiRelated(query : String) : Result<List<EntityWiki>> {
        return callApi(
            api = { networkApi.getListWikiRelated(query) },
            mapping = { it.body()?.pages?.map { data -> data.asEntity() } ?: emptyList() }
        )
    }

    override suspend fun getWikiSummary(query : String) : Result<EntityWiki> {
        return callApi(
            api = { networkApi.getWikiSummary(query) },
            mapping = { it.body()?.asEntity() ?: EntityWiki() }
        )
    }
}

