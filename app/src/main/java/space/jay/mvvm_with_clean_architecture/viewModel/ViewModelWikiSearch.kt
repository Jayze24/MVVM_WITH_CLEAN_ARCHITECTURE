package space.jay.mvvm_with_clean_architecture.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import space.jay.mvvm_with_clean_architecture.domain.entity.EntityWiki
import space.jay.mvvm_with_clean_architecture.domain.useCase.UseCaseGetListWikiRelated
import space.jay.mvvm_with_clean_architecture.domain.useCase.UseCaseGetWikiSummary
import javax.inject.Inject

@HiltViewModel
class ViewModelWikiSearch @Inject constructor(
    val savedStateHandle : SavedStateHandle,
    private val useCaseGetWikiSummary : UseCaseGetWikiSummary,
    private val useCaseGetListWikiRelated : UseCaseGetListWikiRelated
) : ViewModel() {

    companion object {
        const val QUERY = "QUERY"
        const val LIST_RELATED = "LIST_RELATED"
    }

    val searchedWiki : Flow<EntityWiki?> = savedStateHandle
        .getStateFlow<String?>(QUERY, null)
        .filterNotNull()
        .debounce(600)
        .mapLatest { useCaseGetWikiSummary(query = it) }
        .onEach { savedStateHandle[LIST_RELATED] = null }

    val relatedListSearchedQuery : Flow<List<EntityWiki>?> = savedStateHandle
        .getStateFlow(LIST_RELATED, null)

    fun search(query : String?) {
        savedStateHandle[QUERY] = query
    }

    fun related() {
        viewModelScope.launch {
            savedStateHandle.get<String?>(QUERY)?.also { query ->
                savedStateHandle[LIST_RELATED] = useCaseGetListWikiRelated(query = query)
            }
        }
    }
}