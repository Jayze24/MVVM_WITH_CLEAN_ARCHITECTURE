package space.jay.mvvm_with_clean_architecture.core.data.repository

import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryPokemonGo
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture.core.network.SourcePokemonGo
import javax.inject.Inject

class RepositoryPokemonGoImpl @Inject constructor(
    private val source : SourcePokemonGo,
) : RepositoryPokemonGo {

    override suspend fun getListPokemon() : Result<List<EntityPokemon>> {
        return source.getListPokemon()
    }

    override suspend fun getListMegaPokemon() : Result<List<EntityPokemon>> {
        return source.getListMegaPokemon()
    }

    override suspend fun getListPokemonByGeneration(generation : Int) : Result<List<EntityPokemon>> {
        return source.getListPokemonByGeneration(generation)
    }

    override suspend fun getPokemon(number : Int) : Result<EntityPokemon?> {
        return source.getPokemonByNumber(number)
    }

    override suspend fun getPokemon(id : String) : Result<EntityPokemon?> {
        return source.getPokemonByName(id)
    }

}