package space.jay.mvvm_with_clean_architecture._feature.searchWiki

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import space.jay.mvvm_with_clean_architecture._core.common.delay.DelayMessage
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Fail
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.NetworkError
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetWikiData
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._feature.searchWiki.state.StateUIWikiSearch
import space.jay.mvvm_with_clean_architecture._feature.searchWiki.state.StateViewModelWikiSearch
import javax.inject.Inject

@HiltViewModel
class ViewModelWikiSearch @Inject constructor(
    private val getQueryWikiData : UseCaseGetWikiData,
) : ViewModel() {

    private val stateViewModel = MutableStateFlow(StateViewModelWikiSearch())
    val stateUI : StateFlow<StateUIWikiSearch> = stateViewModel
        .map(StateViewModelWikiSearch::toStateUi)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            stateViewModel.value.toStateUi()
        )

    private val delayQuery = DelayMessage<String>()
    private val flowQuery = delayQuery
        .flowResult
        .mapLatest(this::updateUISearch)
        .launchIn(viewModelScope)

    override fun onCleared() {
        super.onCleared()
        flowQuery.cancel()
    }

    fun search(query : String, isDirect : Boolean = false) {
        stateViewModel.update { it.copy(searchInput = query) }
        delayQuery.setMessage(query, isDirect)
    }

    private suspend fun updateUISearch(query : String) {
        stateViewModel.update { it.copy(isLoading = true) }
        stateViewModel.update { state ->
            when (val result = getQueryWikiData(query)) {
                is Success -> state.copy(isLoading = false, data = result.data)
                is NetworkError -> state.copy(
                    isLoading = false,
                    errorMessage = state.errorMessage + ErrorMessage(message = "${result.code} ${result.message}"),
                    data = null
                )
                is Fail -> state.copy(
                    isLoading = false,
                    errorMessage = state.errorMessage + ErrorMessage(message = "${result.throwable}"),
                    data = null
                )
            }
        }
    }
}