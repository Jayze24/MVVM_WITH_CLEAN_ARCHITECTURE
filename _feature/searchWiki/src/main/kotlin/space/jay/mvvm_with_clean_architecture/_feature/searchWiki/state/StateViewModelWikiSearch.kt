package space.jay.mvvm_with_clean_architecture._feature.searchWiki.state

import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki
import java.util.UUID

// todo jay ErrorMessage 다른 곳으로 이동하기
data class ErrorMessage(
    val id : Long = UUID.randomUUID().mostSignificantBits,
    val message : String
)

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