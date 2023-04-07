package space.jay.mvvm_with_clean_architecture._feature.searchWiki

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Fail
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.NetworkError
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetWikiData
import space.jay.mvvm_with_clean_architecture._feature.searchWiki.state.ErrorMessage
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

    fun search(query : String?) {
        query?.also { input ->
            stateViewModel.update {
                it.copy(
                    searchInput = input,
                    isLoading = true
                )
            }
            viewModelScope.launch {
                val result = getQueryWikiData(query)
                stateViewModel.update { state ->
                    when (result) {
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
    }
}