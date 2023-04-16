package space.jay.mvvm_with_clean_architecture.feature.pokemon.listPokemon

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon

sealed interface StateUIListPokemon {

    val searchInput : String
    val errorMessage : List<ErrorMessage>

    data class Loading(
        override val searchInput : String,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIListPokemon

    data class NoData(
        override val searchInput : String,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIListPokemon

    data class HasData(
        override val searchInput : String,
        override val errorMessage : List<ErrorMessage>,
        val listData : List<EntityPokemon>
    ) : StateUIListPokemon
}
