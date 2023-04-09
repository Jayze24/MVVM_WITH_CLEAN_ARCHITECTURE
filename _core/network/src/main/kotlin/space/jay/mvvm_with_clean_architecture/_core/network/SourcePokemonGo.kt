package space.jay.mvvm_with_clean_architecture._core.network

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture._core.network.model.DataPokemon

interface SourcePokemonGo {

    suspend fun getListPokemon() : Result<List<DataPokemon>>

    suspend fun getListMegaPokemon() : Result<List<DataPokemon>>

    suspend fun getListPokemonByGeneration(generation : Int) : Result<List<DataPokemon>>

    suspend fun getPokemonByNumber(id : String) : Result<List<DataPokemon>>
}