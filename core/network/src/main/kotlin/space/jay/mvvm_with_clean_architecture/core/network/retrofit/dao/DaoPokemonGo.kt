package space.jay.mvvm_with_clean_architecture.core.network.retrofit.dao

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import space.jay.mvvm_with_clean_architecture.core.network.model.DataPokemon

interface DaoPokemonGo {

    @GET("/pokemon-go-api/api/pokedex.json")
    suspend fun getListPokemon() : Response<List<DataPokemon>>

    @GET("/pokemon-go-api/api/pokedex/mega.json")
    suspend fun getListMegaPokemon() : Response<List<DataPokemon>>

    @GET("/pokemon-go-api/api/pokedex/generation/{generation}.json")
    suspend fun getListPokemonByGeneration(@Path("generation") generation : Int) : Response<List<DataPokemon>>

    @GET("/pokemon-go-api/api/pokedex/id/{id}.json")
    suspend fun getPokemonByNumber(@Path("id") dexNr : Int) : Response<DataPokemon?>

    @GET("/pokemon-go-api/api/pokedex/name/{name}.json")
    suspend fun getPokemonByName(@Path("name") id : String) : Response<DataPokemon?>
}