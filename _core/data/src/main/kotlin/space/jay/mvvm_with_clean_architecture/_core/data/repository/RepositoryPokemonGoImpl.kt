package space.jay.mvvm_with_clean_architecture._core.data.repository

import space.jay.mvvm_with_clean_architecture._core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture._core.data.RepositoryPokemonGo
import space.jay.mvvm_with_clean_architecture._core.data.RepositoryWiki
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki
import space.jay.mvvm_with_clean_architecture._core.network.SourcePokemonGo
import space.jay.mvvm_with_clean_architecture._core.network.SourceWiki
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