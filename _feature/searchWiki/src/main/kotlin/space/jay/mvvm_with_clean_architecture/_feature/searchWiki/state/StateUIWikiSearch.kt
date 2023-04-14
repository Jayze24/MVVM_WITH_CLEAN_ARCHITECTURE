package space.jay.mvvm_with_clean_architecture._feature.searchWiki.state

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture.core.model.wiki.EntityWiki

sealed interface StateUIWikiSearch {

    val searchInput : String
    val errorMessage : List<ErrorMessage>

    data class Loading(
        override val searchInput : String,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIWikiSearch

    data class NoData(
        override val searchInput : String,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIWikiSearch

    data class HasData(
        override val searchInput : String,
        override val errorMessage : List<ErrorMessage>,
        val data : EntityWiki
    ) : StateUIWikiSearch
}
