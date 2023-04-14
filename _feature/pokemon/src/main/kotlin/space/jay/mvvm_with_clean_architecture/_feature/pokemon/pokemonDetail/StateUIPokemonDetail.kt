package space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon

sealed interface StateUIPokemonDetail {

    val isOpen : Boolean
    val errorMessage : List<ErrorMessage>

    data class Loading(
        override val isOpen : Boolean,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIPokemonDetail

    data class NoData(
        override val isOpen : Boolean,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIPokemonDetail

    data class HasData(
        override val isOpen : Boolean,
        override val errorMessage : List<ErrorMessage>,
        val data : EntityPokemon
    ) : StateUIPokemonDetail
}
