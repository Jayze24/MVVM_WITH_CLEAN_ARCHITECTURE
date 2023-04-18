package space.jay.mvvm_with_clean_architecture.core.domain.fake

import android.content.Context
import com.google.gson.Gson
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryPokemonGo
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture.core.network.R
import space.jay.mvvm_with_clean_architecture.core.network.model.DataPokemon
import space.jay.mvvm_with_clean_architecture.core.network.model.asEntity
import space.jay.mvvm_with_clean_architecture.core.test.common.FileReader

class FakeRepositoryPokemonGo(private val context : Context) : RepositoryPokemonGo {

    private val fileReader = FileReader()

    override suspend fun getListPokemon() : Result<List<EntityPokemon>> {
        val json = fileReader.readJson(context = context, "pokedex_mega")
        val list = Gson().fromJson(json, Array<DataPokemon>::class.java).map { it.asEntity(context.getString(R.string.language_pokemon_go)) }
        return Success(list)
    }

    override suspend fun getListMegaPokemon() : Result<List<EntityPokemon>> {
        val json = fileReader.readJson(context = context, "pokedex_mega")
        val list = Gson().fromJson<List<EntityPokemon>>(json, Array<EntityPokemon>::class.java)
        return Success(list)
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