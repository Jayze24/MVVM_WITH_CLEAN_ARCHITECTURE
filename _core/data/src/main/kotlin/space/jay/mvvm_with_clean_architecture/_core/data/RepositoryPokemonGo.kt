package space.jay.mvvm_with_clean_architecture._core.data

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki

interface RepositoryPokemonGo {

    suspend fun getListPokemon() : Result<List<EntityPokemon>>

    suspend fun getListMegaPokemon() : Result<List<EntityPokemon>>

    suspend fun getListPokemonByGeneration(generation : Int) : Result<List<EntityPokemon>>

    suspend fun getPokemon(number : Int) : Result<EntityPokemon?>

    suspend fun getPokemon(id : String) : Result<EntityPokemon?>

}