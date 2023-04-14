package space.jay.mvvm_with_clean_architecture._core.network

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon

interface SourcePokemonGo {

    suspend fun getListPokemon() : Result<List<EntityPokemon>>

    suspend fun getListMegaPokemon() : Result<List<EntityPokemon>>

    suspend fun getListPokemonByGeneration(generation : Int) : Result<List<EntityPokemon>>

    suspend fun getPokemonByNumber(dexNr : Int) : Result<EntityPokemon?>

    suspend fun getPokemonByName(id : String) : Result<EntityPokemon?>
}