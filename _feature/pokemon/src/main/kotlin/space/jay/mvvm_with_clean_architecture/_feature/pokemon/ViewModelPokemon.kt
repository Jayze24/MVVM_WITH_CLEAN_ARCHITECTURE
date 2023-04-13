package space.jay.mvvm_with_clean_architecture._feature.pokemon

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
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Fail
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.NetworkError
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetListMegaPokemon
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetListPokemon
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetPokemon
import space.jay.mvvm_with_clean_architecture._feature.pokemon.listPokemon.StateUIListPokemon
import space.jay.mvvm_with_clean_architecture._feature.pokemon.listPokemon.StateViewModelListPokemon
import space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail.StateViewModelPokemonDetail
import javax.inject.Inject

@HiltViewModel
class ViewModelPokemon @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val useCaseGetListPokemon : UseCaseGetListPokemon,
    private val useCaseGetListMegaPokemon : UseCaseGetListMegaPokemon,
    private val useCaseGetPokemon : UseCaseGetPokemon
) : ViewModel() {

    private val stateViewModelListPokemon = MutableStateFlow(StateViewModelListPokemon(isMegaPokemon = savedStateHandle[argIsMega] ?: false))
    val stateUIListPokemon : StateFlow<StateUIListPokemon> = stateViewModelListPokemon
        .map(StateViewModelListPokemon::toStateUi)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            stateViewModelListPokemon.value.toStateUi()
        )

    private val stateViewModelPokemonDetail = MutableStateFlow(StateViewModelPokemonDetail(isLoading = true))
    val stateUIPokemonDetail = stateViewModelPokemonDetail
        .map(StateViewModelPokemonDetail::toStateUi)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            stateViewModelPokemonDetail.value.toStateUi()
        )

    init {
        viewModelScope.launch {
            getListPokemon()
        }
    }

    fun search(name : String) {
        stateViewModelListPokemon.update { it.copy(searchInput = name) }
    }

    suspend fun getListPokemon() {
        stateViewModelListPokemon.update { it.copy(isLoading = true) }
        stateViewModelListPokemon.update { state ->
            when (val result = if (state.isMegaPokemon) useCaseGetListMegaPokemon() else useCaseGetListPokemon()) {
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

    fun getPokemonDetail(number : Int) {
        if (number == 0) {
            stateViewModelPokemonDetail.update { it.copy(isLoading = false, dataOriginal = null) }
            return
        }
        viewModelScope.launch {
            stateViewModelPokemonDetail.update { it.copy(isLoading = true, isOpen = true, number = number) }
            stateViewModelPokemonDetail.update { state ->
                when (val result = useCaseGetPokemon(number)) {
                    is Success -> state.copy(isLoading = false, dataOriginal = result.data)
                    is NetworkError -> state.copy(
                        isLoading = false,
                        errorMessage = state.errorMessage + ErrorMessage(message = "${result.code} ${result.message}"),
                        dataOriginal = null
                    )
                    is Fail -> state.copy(
                        isLoading = false,
                        errorMessage = state.errorMessage + ErrorMessage(message = "${result.throwable}"),
                        dataOriginal = null
                    )
                }
            }
        }
    }

    fun closePokemonDetail() {
        stateViewModelPokemonDetail.update { it.copy(isOpen = false) }
    }
}