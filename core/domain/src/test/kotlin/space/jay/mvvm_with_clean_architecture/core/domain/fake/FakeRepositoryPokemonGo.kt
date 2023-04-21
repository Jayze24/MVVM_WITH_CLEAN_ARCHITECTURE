package space.jay.mvvm_with_clean_architecture.core.domain.fake

import android.content.Context
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryPokemonGo
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture.core.test.fakeData.FakeDataPokemon

class FakeRepositoryPokemonGo(private val context : Context) : RepositoryPokemonGo {

    private val fakeDataPokemon = FakeDataPokemon(context)

    override suspend fun getListPokemon() : Result<List<EntityPokemon>> {
        return Success(fakeDataPokemon.getListEntityPokemon())
    }

    override suspend fun getListMegaPokemon() : Result<List<EntityPokemon>> {
        return Success(fakeDataPokemon.getListMegaEntityPokemon())
    }

    override suspend fun getListPokemonByGeneration(generation : Int) : Result<List<EntityPokemon>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemon(number : Int) : Result<EntityPokemon?> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemon(id : String) : Result<EntityPokemon?> {
        TODO("Not yet implemented")
    }
}