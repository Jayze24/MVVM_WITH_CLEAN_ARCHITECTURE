package space.jay.mvvm_with_clean_architecture._core.network.model

import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityEvolution
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityMegaEvolution
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntitySkill
import java.util.*

data class DataPokemon(
    val id : String = "",
    val formId : String = "",
    val dexNr : Int = 0,
    val generation : Int = 0,
    val names : Map<String, String> = emptyMap(),
    val stats : Stats? = null,
    val primaryType : TypePokemon = TypePokemon(),
    val secondaryType : TypePokemon? = null,
    val pokemonClass : String? = null,
    val quickMoves : LinkedHashMap<String, Skill> = linkedMapOf(),
    val cinematicMoves : LinkedHashMap<String, Skill> = linkedMapOf(),
    val eliteQuickMoves : LinkedHashMap<String, Skill>? = null,
    val eliteCinematicMoves : LinkedHashMap<String, Skill>? = null,
    val assets : Assets? = null,
    val assetForms : List<AssetForm> = listOf(),
    val regionForms : LinkedHashMap<String, DataPokemon>? = null,
    val evolutions : List<Evolution> = listOf(),
    val hasMegaEvolution : Boolean = false,
    val megaEvolutions : LinkedHashMap<String, MegaEvolution>? = null,
)

// data class Names(
//     val English: String = "",
//     val French: String = "",
//     val German: String = "",
//     val Italian: String = "",
//     val Japanese: String = "",
//     val Korean: String = "",
//     val Spanish: String = ""
// )

data class Stats(
    val attack : Int = 0,
    val defense : Int = 0,
    val stamina : Int = 0
)

data class TypePokemon(
    val type : String = "",
    val names : Map<String, String> = emptyMap(),
)

data class Skill(
    val id : String = "",
    val power : Int = 0,
    val energy : Int = 0,
    val durationMs : Int = 0,
    val type : Type = Type(),
    val names : Map<String, String> = emptyMap(),
    val combat : Combat = Combat(),
)

data class Type(
    val type : String = "",
    val names : Map<String, String> = emptyMap(),
)

data class Combat(
    val energy : Int = 0,
    val power : Int = 0,
    val turns : Int = 0,
    val buffs : Buffs? = null
)

data class Buffs(
    val activationChance : Int? = null,
    val attackerAttackStatsChange : Int? = null,
    val attackerDefenseStatsChange : Int? = null,
    val targetAttackStatsChange : Int? = null,
    val targetDefenseStatsChange : Int? = null
)

data class Assets(
    val image : String? = null,
    val shinyImage : String? = null
)

data class AssetForm(
    val form : String? = null,
    val costume : String? = null,
    val isFemale : Boolean? = null,
    val image : String? = null,
    val shinyImage : String? = null
)

data class Evolution(
    val id : String = "",
    val formId : String = "",
    val candies : Int = 0,
    val item : Item? = null,
    val quests : List<Quests> = emptyList()
)

data class Item(
    val id : String = "",
    val names : Map<String, String> = emptyMap(),
)

data class Quests(
    val id : String = "",
    val type : String = "",
    val names : Map<String, String> = emptyMap(),
)

data class MegaEvolution(
    val id : String = "",
    val names : Map<String, String> = emptyMap(),
    val stats : Stats? = null,
    val primaryType : TypePokemon = TypePokemon(),
    val secondaryType : TypePokemon? = null,
    val assets : Assets? = null,
)

fun DataPokemon.asEntity(language : String = Locale.getDefault().displayLanguage) = EntityPokemon(
    id = id,
    number = dexNr,
    generation = generation,
    name = names.getName(language),
    typePrimary = primaryType.getName(language) ?: "",
    typeSecondary = secondaryType.getName(language),
    stamina = stats?.stamina ?: 0,
    attack = stats?.attack ?: 0,
    defense = stats?.defense ?: 0,
    pokemonClass = pokemonClass,
    skillQuick = quickMoves.values.map { it.asEntity(language) },
    skillCinematic = cinematicMoves.values.map { it.asEntity(language) },
    skillEliteQuick = eliteQuickMoves?.values?.map { it.asEntity(language) },
    skillEliteCinematic = eliteCinematicMoves?.values?.map { it.asEntity(language) },
    imageNormal = assets?.image,
    imageShiny = assets?.shinyImage,
    imageCostume = assetForms.filter { !it.costume.isNullOrBlank() && !it.image.isNullOrBlank() }.map { it.image!! }.toSet(),
    evolutions = evolutions.map { it.asEntity(language) },
    megaEvolutions = megaEvolutions?.values?.map { it.asEntity(language) }
)

private fun Map<String, String>.getName(language : String = Locale.getDefault().displayLanguage) : String {
    return this[language] ?: this["English"] ?: ""
}

private fun TypePokemon?.getName(language : String = Locale.getDefault().displayLanguage) : String? {
    return if (this == null) null else this.names[language] ?: this.names["English"] ?: this.type
}

private fun Skill.asEntity(language : String = Locale.getDefault().displayLanguage) = EntitySkill(
    name = names.getName(language),
    type = type.names.getName(language),
    power = power,
    energy = energy,
    durationMs = durationMs,
    combatEnergy = combat.energy,
    combatPower = combat.energy,
    combatTurns = combat.turns,
    combatActivationChance = combat.buffs?.activationChance,
    combatAttackerAttackStatsChange = combat.buffs?.attackerAttackStatsChange,
    combatAttackerDefenseStatsChange = combat.buffs?.attackerDefenseStatsChange,
    combatTargetAttackStatsChange = combat.buffs?.targetAttackStatsChange,
    combatTargetDefenseStatsChange = combat.buffs?.targetDefenseStatsChange
)

private fun Evolution.asEntity(language : String = Locale.getDefault().displayLanguage) = EntityEvolution(
    id = id,
    candies = candies,
    item = item?.names?.getName(language),
    questDescription = quests.fold("") { acc, quests ->
        val q = quests.names.getName(language)
        if (q.isBlank()) acc else "$acc\n$q"
    }
)

private fun MegaEvolution.asEntity(language : String = Locale.getDefault().displayLanguage) = EntityMegaEvolution(
    name = names.getName(language),
    typePrimary = primaryType.names.getName(language),
    typeSecondary = secondaryType?.names?.getName(language),
    stamina = stats?.stamina ?: 0,
    attack = stats?.attack ?: 0,
    defense = stats?.defense ?: 0,
    imageNormal = assets?.image,
    imageShiny = assets?.shinyImage
)