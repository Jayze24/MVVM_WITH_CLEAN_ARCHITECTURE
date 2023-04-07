package space.jay.mvvm_with_clean_architecture._feature.searchWiki

import androidx.annotation.StringRes
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki

data class ErrorMessage(val id: Long, @StringRes val messageId: Int)

data class StateViewModelWikiSearch(
    val searchInput : String = "",
    val isLoading : Boolean = false,
    val errorMessage: List<ErrorMessage> = emptyList(),
    val data : EntityWiki? = null
)