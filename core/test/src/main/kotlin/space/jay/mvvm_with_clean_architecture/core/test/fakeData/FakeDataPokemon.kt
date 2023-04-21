package space.jay.mvvm_with_clean_architecture.core.test.fakeData

import android.content.Context
import com.google.gson.Gson
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture.core.network.model.DataPokemon
import space.jay.mvvm_with_clean_architecture.core.network.model.asEntity
import space.jay.mvvm_with_clean_architecture.core.test.R
import space.jay.mvvm_with_clean_architecture.core.test.common.FileReader

class FakeDataPokemon(private val context : Context) {

    private val language = context.getString(space.jay.mvvm_with_clean_architecture.core.network.R.string.language_pokemon_go)

    fun getJsonListDataPokemon() : String = FileReader.readRaw(context, R.raw.pokedex)
    fun getJsonListMegaDataPokemon() : String = FileReader.readRaw(context, R.raw.pokedex_mega)
    fun getJsonListGeneration9DataPokemon() : String = FileReader.readRaw(context, R.raw.pokedex_generation_9)
    fun getJsonDataPokemonId1() = FileReader.readRaw(context, R.raw.pokedex_id_1)
    fun getJsonDataPokemonId3() = FileReader.readRaw(context, R.raw.pokedex_id_3)
    fun getJsonDataPokemonId901() = FileReader.readRaw(context, R.raw.pokedex_id_901)

    fun getListEntityPokemon() : List<EntityPokemon> = Gson()
        .fromJson(getJsonListDataPokemon(), Array<DataPokemon>::class.java)
        .map { it.asEntity(language) }

    fun getListMegaEntityPokemon() : List<EntityPokemon> = Gson()
        .fromJson(getJsonListMegaDataPokemon(), Array<DataPokemon>::class.java)
        .map { it.asEntity(language) }

    fun getEntityPokemonId1() : EntityPokemon = Gson()
        .fromJson(getJsonDataPokemonId1(), DataPokemon::class.java)
        .asEntity(language)

    fun getEntityPokemonId3() : EntityPokemon = Gson()
        .fromJson(getJsonDataPokemonId3(), DataPokemon::class.java)
        .asEntity(language)

    fun getEntityPokemonId901() : EntityPokemon = Gson()
        .fromJson(getJsonDataPokemonId901(), DataPokemon::class.java)
        .asEntity(language)

}