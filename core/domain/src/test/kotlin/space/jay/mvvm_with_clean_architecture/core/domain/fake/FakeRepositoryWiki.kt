package space.jay.mvvm_with_clean_architecture.core.domain.fake

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ClientError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ServerError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryWiki
import space.jay.mvvm_with_clean_architecture.core.model.wiki.EntityWiki

class FakeRepositoryWiki(private val fakeListRelated : List<EntityWiki>? = null, private val fakeWiki : EntityWiki? = null) : RepositoryWiki {

    override suspend fun getListWikiRelated(query : String) : Result<List<EntityWiki>> {
        return if (query.isEmpty()) {
            ClientError(404, "")
        } else if (fakeListRelated != null) {
            Success(fakeListRelated)
        } else {
            ServerError(500, "")
        }
    }

    override suspend fun getWikiSummary(query : String) : Result<EntityWiki> {
        return if (query.isEmpty()) {
            ClientError(404, "")
        } else if (fakeWiki != null) {
            Success(fakeWiki)
        } else {
            ServerError(500, "")
        }
    }
}