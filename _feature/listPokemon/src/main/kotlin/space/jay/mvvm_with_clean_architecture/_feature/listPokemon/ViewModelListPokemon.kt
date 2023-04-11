package space.jay.mvvm_with_clean_architecture._feature.listPokemon

import androidx.lifecycle.SavedStateHandle
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
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetListMegaPokemon
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetListPokemon
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._feature.listPokemon.state.StateUIListPokemon
import space.jay.mvvm_with_clean_architecture._feature.listPokemon.state.StateViewModelListPokemon
import javax.inject.Inject

const val EXTRA_IS_MEGA = "EXTRA_IS_MEGA"

@HiltViewModel
class ViewModelListPokemon @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val getListPokemon : UseCaseGetListPokemon,
    private val getListMegaPokemon : UseCaseGetListMegaPokemon
) : ViewModel() {

    private val stateViewModel = MutableStateFlow(
        StateViewModelListPokemon(isMegaPokemon = savedStateHandle[EXTRA_IS_MEGA] ?: false)
    )
    val stateUI : StateFlow<StateUIListPokemon> = stateViewModel
        .map(StateViewModelListPokemon::toStateUi)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            stateViewModel.value.toStateUi()
        )

    init {
        viewModelScope.launch {
            getList()
        }
    }

    fun search(name: String) {
        stateViewModel.update { it.copy(searchInput = name) }
    }

    fun getPokemon(number: Int) {
        // todo 상세 페이지로 넘기기
    }

    suspend fun getList() {
        stateViewModel.update { it.copy(isLoading = true) }
        stateViewModel.update { state ->
            when (val result = if (state.isMegaPokemon) getListMegaPokemon() else getListPokemon()) {
                is Success -> state.copy(isLoading = false, listDataOriginal = result.data)
                is NetworkError -> state.copy(
                    isLoading = false,
                    errorMessage = state.errorMessage + ErrorMessage(message = "${result.code} ${result.message}"),
                    listDataOriginal = emptyList()
                )
                is Fail -> state.copy(
                    isLoading = false,
                    errorMessage = state.errorMessage + ErrorMessage(message = "${result.throwable}"),
                    listDataOriginal = emptyList()
                )
            }
        }
    }

}