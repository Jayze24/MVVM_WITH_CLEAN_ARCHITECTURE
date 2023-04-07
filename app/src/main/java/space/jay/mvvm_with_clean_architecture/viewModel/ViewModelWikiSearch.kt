package space.jay.mvvm_with_clean_architecture.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ClientError
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Fail
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ServerError
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetWikiDataWithRelatedTopic
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ViewModelWikiSearch @Inject constructor(
    val savedStateHandle : SavedStateHandle,
    private val getQueryWikiData : UseCaseGetWikiDataWithRelatedTopic
) : ViewModel() {

    companion object {
        const val QUERY = "QUERY"
        const val LIST_RELATED = "LIST_RELATED"
    }

    private val _uiState = MutableStateFlow(UiStateWiki())
    val uiStateWiki : StateFlow<UiStateWiki> = _uiState

    fun search(query : String?) {
        savedStateHandle[QUERY] = query
        query?.also {
            _uiState.update {
                UiStateWiki(isLoading = true)
            }
            viewModelScope.launch {
                val result = getQueryWikiData(query)
                _uiState.update {
                    UiStateWiki().apply {
                        when(result) {
                            is Success -> this.data = result.data
                            is ClientError -> this.errorMessage = "${result.code} ${result.message}"
                            is ServerError -> this.errorMessage = "${result.code} ${result.message}"
                            is Fail -> this.errorMessage = "${result.throwable}"
                        }
                    }
                }
            }
        }
    }
}

data class UiStateWiki(
    var isLoading : Boolean = false,
    var data : EntityWiki? = null,
    var errorMessage: String? = null,
    var id : Long = UUID.randomUUID().leastSignificantBits
)