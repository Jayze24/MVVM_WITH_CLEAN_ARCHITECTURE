package space.jay.mvvm_with_clean_architecture._core.network.retrofit.dao

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import space.jay.mvvm_with_clean_architecture._core.network.model.DataPokemon

interface DaoPokemonGo {

    @GET("/api/pokedex.json")
    suspend fun getListPokemon() : Response<List<DataPokemon>>

    @GET("/api/pokedex/mega.json")
    suspend fun getListMegaPokemon() : Response<List<DataPokemon>>

    @GET("/api/pokedex/generation/{generation}.json")
    suspend fun getListPokemonByGeneration(@Path("generation") generation : Int) : Response<List<DataPokemon>>

    @GET("/api/pokedex/id/{id}.json")
    suspend fun getPokemonByNumber(@Path("id") id : String) : Response<List<DataPokemon>>
}