package space.jay.mvvm_with_clean_architecture._feature.listPokemon.state

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon

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
