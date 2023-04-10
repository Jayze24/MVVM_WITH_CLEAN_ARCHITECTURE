package space.jay.mvvm_with_clean_architecture._core.network.retrofit

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture._core.network.SourcePokemonGo
import space.jay.mvvm_with_clean_architecture._core.network.model.asEntity
import space.jay.mvvm_with_clean_architecture._core.network.retrofit.dao.DaoPokemonGo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetworkPokemonGo @Inject internal constructor(
    private val networkApi : DaoPokemonGo
) : BaseRetrofitNetwork(), SourcePokemonGo {

    override suspend fun getListPokemon() : Result<List<EntityPokemon>> {
        return callApi(
            api = { networkApi.getListPokemon() },
            mapping = { result -> result.body()?.map { it.asEntity() } ?: emptyList() }
        )
    }

    override suspend fun getListMegaPokemon() : Result<List<EntityPokemon>> {
        return callApi(
            api = { networkApi.getListMegaPokemon() },
            mapping = { result -> result.body()?.map { it.asEntity() } ?: emptyList() }
        )
    }

    override suspend fun getListPokemonByGeneration(generation : Int) : Result<List<EntityPokemon>> {
        return callApi(
            api = { networkApi.getListPokemonByGeneration(generation) },
            mapping = { result -> result.body()?.map { it.asEntity() } ?: emptyList() }
        )
    }

    override suspend fun getPokemonByNumber(dexNr : Int) : Result<EntityPokemon?> {
        return callApi(
            api = { networkApi.getPokemonByNumber(dexNr) },
            mapping = { it.body()?.asEntity() }
        )
    }

    override suspend fun getPokemonByName(id : String) : Result<EntityPokemon?> {
        return callApi(
            api = { networkApi.getPokemonByName(id) },
            mapping = { it.body()?.asEntity() }
        )
    }
}

