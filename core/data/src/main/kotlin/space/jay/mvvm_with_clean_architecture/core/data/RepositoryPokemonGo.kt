package space.jay.mvvm_with_clean_architecture.core.data

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon

interface RepositoryPokemonGo {

    suspend fun getListPokemon() : Result<List<EntityPokemon>>

    suspend fun getListMegaPokemon() : Result<List<EntityPokemon>>

    suspend fun getListPokemonByGeneration(generation : Int) : Result<List<EntityPokemon>>

    suspend fun getPokemon(number : Int) : Result<EntityPokemon?>

    suspend fun getPokemon(id : String) : Result<EntityPokemon?>

}