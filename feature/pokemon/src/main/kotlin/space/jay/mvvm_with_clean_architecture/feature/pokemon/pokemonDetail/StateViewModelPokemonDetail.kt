package space.jay.mvvm_with_clean_architecture.feature.pokemon.pokemonDetail

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon

data class StateViewModelPokemonDetail(
    val number : Int = 0,
    val isLoading : Boolean = false,
    val isVisible : Boolean = false,
    val errorMessage : List<ErrorMessage> = emptyList(),
    val dataOriginal : EntityPokemon? = null
) {

    fun toStateUi() : StateUIPokemonDetail {
        return when {
            isLoading -> StateUIPokemonDetail.Loading(isVisible, errorMessage)
            dataOriginal != null -> StateUIPokemonDetail.HasData(isVisible, errorMessage, dataOriginal)
            else -> StateUIPokemonDetail.NoData(isVisible, errorMessage)
        }
    }
}