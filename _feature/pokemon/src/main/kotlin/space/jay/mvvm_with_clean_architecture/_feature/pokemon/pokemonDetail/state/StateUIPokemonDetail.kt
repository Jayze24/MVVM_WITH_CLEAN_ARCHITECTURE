package space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail.state

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.ErrorMessage
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon

sealed interface StateUIPokemonDetail {

    val number : Int
    val errorMessage : List<ErrorMessage>

    data class Loading(
        override val number : Int,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIPokemonDetail

    data class NoData(
        override val number : Int,
        override val errorMessage : List<ErrorMessage>
    ) : StateUIPokemonDetail

    data class HasData(
        override val number : Int,
        override val errorMessage : List<ErrorMessage>,
        val data : EntityPokemon
    ) : StateUIPokemonDetail
}
