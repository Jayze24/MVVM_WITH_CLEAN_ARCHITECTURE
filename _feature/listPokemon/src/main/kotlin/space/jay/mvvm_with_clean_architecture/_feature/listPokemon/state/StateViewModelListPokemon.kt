package space.jay.mvvm_with_clean_architecture._feature.listPokemon.state

import space.jay.mvvm_with_clean_architecture._core.model.ErrorMessage
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon

data class StateViewModelListPokemon(
    val searchInput : String = "",
    val isLoading : Boolean = false,
    val isMegaPokemon : Boolean = false,
    val errorMessage : List<ErrorMessage> = emptyList(),
    val listDataOriginal : List<EntityPokemon> = emptyList()
) {

    fun toStateUi() : StateUIListPokemon {
        val list = listDataOriginal.filter { it.name.contains(searchInput) }
        return when {
            isLoading -> StateUIListPokemon.Loading(searchInput, errorMessage)
            list.isNotEmpty() -> StateUIListPokemon.HasData(searchInput, errorMessage, list)
            else -> StateUIListPokemon.NoData(searchInput, errorMessage)
        }
    }
}