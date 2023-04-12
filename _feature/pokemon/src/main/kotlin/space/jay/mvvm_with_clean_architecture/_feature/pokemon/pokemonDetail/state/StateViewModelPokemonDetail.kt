package space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail.state

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon

data class StateViewModelPokemonDetail(
    val number : Int = 0,
    val isLoading : Boolean = false,
    val errorMessage : List<ErrorMessage> = emptyList(),
    val dataOriginal : EntityPokemon? = null
) {

    fun toStateUi() : StateUIPokemonDetail {
        return when {
            isLoading -> StateUIPokemonDetail.Loading(number, errorMessage)
            dataOriginal != null -> StateUIPokemonDetail.HasData(number, errorMessage, dataOriginal)
            else -> StateUIPokemonDetail.NoData(number, errorMessage)
        }
    }
}