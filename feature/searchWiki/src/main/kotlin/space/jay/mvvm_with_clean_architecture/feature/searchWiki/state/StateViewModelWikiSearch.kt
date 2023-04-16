package space.jay.mvvm_with_clean_architecture.feature.searchWiki.state

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture.core.model.wiki.EntityWiki

data class StateViewModelWikiSearch(
    val searchInput : String = "",
    val isLoading : Boolean = false,
    val errorMessage : List<ErrorMessage> = emptyList(),
    val data : EntityWiki? = null
) {
    fun toStateUi() : StateUIWikiSearch =
        when {
            isLoading -> StateUIWikiSearch.Loading(searchInput, errorMessage)
            data != null -> StateUIWikiSearch.HasData(searchInput, errorMessage, data)
            else -> StateUIWikiSearch.NoData(searchInput, errorMessage)
        }
}