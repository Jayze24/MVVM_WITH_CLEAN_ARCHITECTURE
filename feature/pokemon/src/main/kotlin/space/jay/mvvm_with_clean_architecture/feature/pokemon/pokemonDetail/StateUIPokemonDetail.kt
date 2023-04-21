package space.jay.mvvm_with_clean_architecture.feature.pokemon.pokemonDetail

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon

sealed interface StateUIPokemonDetail {

    val isVisible : Boolean
    val errorMessage : List<ErrorMessage>

    data class Loading(
        override val isVisible : Boolean,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIPokemonDetail

    data class NoData(
        override val isVisible : Boolean,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIPokemonDetail

    data class HasData(
        override val isVisible : Boolean,
        override val errorMessage : List<ErrorMessage>,
        val data : EntityPokemon
    ) : StateUIPokemonDetail
}
