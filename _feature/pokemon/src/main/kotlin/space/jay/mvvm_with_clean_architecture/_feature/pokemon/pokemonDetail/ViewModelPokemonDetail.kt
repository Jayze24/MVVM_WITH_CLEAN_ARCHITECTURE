package space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Fail
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.NetworkError
import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture._core.domain.UseCaseGetPokemon
import space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail.state.StateViewModelPokemonDetail
import javax.inject.Inject

@HiltViewModel
class ViewModelPokemonDetail @Inject constructor(
    private val getPokemon : UseCaseGetPokemon
) : ViewModel() {

    private val stateViewModel = MutableStateFlow(
        StateViewModelPokemonDetail(isLoading = true)
    )
    val stateUI =stateViewModel
        .map(StateViewModelPokemonDetail::toStateUi)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            stateViewModel.value.toStateUi()
        )

    fun getPokemonDetail(number : Int) {
        if (number == 0) {
            stateViewModel.update { it.copy(isLoading = false, dataOriginal = null) }
            return
        }
        viewModelScope.launch {
            stateViewModel.update { it.copy(number = number) }
            stateViewModel.update { state ->
                when (val result = getPokemon(number)) {
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
}