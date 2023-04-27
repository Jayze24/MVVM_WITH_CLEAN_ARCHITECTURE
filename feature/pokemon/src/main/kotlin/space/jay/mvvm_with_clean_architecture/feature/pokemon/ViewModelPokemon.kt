package space.jay.mvvm_with_clean_architecture.feature.pokemon

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.jay.mvvm_with_clean_architecture.core.common.di.DispatcherDefault
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Error
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.NetworkError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.domain.UseCaseGetListMegaPokemon
import space.jay.mvvm_with_clean_architecture.core.domain.UseCaseGetListPokemon
import space.jay.mvvm_with_clean_architecture.core.domain.UseCaseGetPokemon
import space.jay.mvvm_with_clean_architecture.feature.pokemon.listPokemon.StateUIListPokemon
import space.jay.mvvm_with_clean_architecture.feature.pokemon.listPokemon.StateViewModelListPokemon
import space.jay.mvvm_with_clean_architecture.feature.pokemon.pokemonDetail.StateViewModelPokemonDetail
import javax.inject.Inject

@HiltViewModel
class ViewModelPokemon @Inject constructor(
    @DispatcherDefault private val dispatcherDefault : CoroutineDispatcher,
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
        getListPokemon()
    }

    fun search(name : String) {
        stateViewModelListPokemon.update { it.copy(searchInput = name) }
    }

    fun getListPokemon() {
        stateViewModelListPokemon.update { it.copy(isLoading = true) }
        // Default dispatcher [https://developer.android.com/topic/architecture/ui-layer/state-production?hl=ko#mutating_the_ui_state_from_background_threads]
        // 계산 집약적인 함수라고 가정 하고 예제로 넣어 봄.
        viewModelScope.launch(dispatcherDefault) {
            stateViewModelListPokemon.update { state ->
                when (val result = if (state.isMegaPokemon) useCaseGetListMegaPokemon() else useCaseGetListPokemon()) {
                    is Success -> state.copy(isLoading = false, listDataOriginal = result.data)
                    is NetworkError -> state.copy(
                        isLoading = false,
                        errorMessage = state.errorMessage + ErrorMessage(message = "${result.code} ${result.message}"),
                        listDataOriginal = emptyList()
                    )
                    is Error -> state.copy(
                        isLoading = false,
                        errorMessage = state.errorMessage + ErrorMessage(message = "${result.message}"),
                        listDataOriginal = emptyList()
                    )
                }
            }
        }
    }

    fun getPokemonDetail() {
        getPokemonDetail(stateViewModelPokemonDetail.value.number)
    }
    fun getPokemonDetail(number : Int) {
        if (number == 0) {
            stateViewModelPokemonDetail.update { it.copy(isLoading = false, dataOriginal = null) }
            return
        }
        viewModelScope.launch {
            stateViewModelPokemonDetail.update { it.copy(isLoading = true, isVisible = true, number = number) }
            stateViewModelPokemonDetail.update { state ->
                when (val result = useCaseGetPokemon(number)) {
                    is Success -> state.copy(isLoading = false, dataOriginal = result.data)
                    is NetworkError -> state.copy(
                        isLoading = false,
                        errorMessage = state.errorMessage + ErrorMessage(message = "${result.code} ${result.message}"),
                        dataOriginal = null
                    )
                    is Error -> state.copy(
                        isLoading = false,
                        errorMessage = state.errorMessage + ErrorMessage(message = "${result.message}"),
                        dataOriginal = null
                    )
                }
            }
        }
    }
    fun dismissedErrorListPokemon(id : Long) {
        stateViewModelListPokemon.update { state ->
            val errorMessage = state.errorMessage.filterNot { it.id == id }
            state.copy(errorMessage = errorMessage)
        }
    }
    fun dismissedErrorPokemonDetail(id : Long) {
        stateViewModelPokemonDetail.update { state ->
            val errorMessage = state.errorMessage.filterNot { it.id == id }
            state.copy(errorMessage = errorMessage)
        }
    }

    fun closePokemonDetail() {
        stateViewModelPokemonDetail.update { it.copy(isVisible = false) }
    }
}