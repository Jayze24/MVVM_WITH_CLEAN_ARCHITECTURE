package space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon

data class StateViewModelPokemonDetail(
    val number : Int = 0,
    val isLoading : Boolean = false,
    val isOpen : Boolean = false,
    val errorMessage : List<ErrorMessage> = emptyList(),
    val dataOriginal : EntityPokemon? = null
) {

    fun toStateUi() : StateUIPokemonDetail {
        return when {
            isOpen and isLoading -> StateUIPokemonDetail.Loading(isOpen, errorMessage)
            dataOriginal != null -> StateUIPokemonDetail.HasData(isOpen, errorMessage, dataOriginal)
            else -> StateUIPokemonDetail.NoData(isOpen, errorMessage)
        }
    }
}