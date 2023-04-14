package space.jay.mvvm_with_clean_architecture.core.model.pokemon

data class EntityPokemon(
    val id : String,
    val number : Int,
    val generation : Int,
    val name : String,
    val typePrimary : String,
    val typeSecondary : String?,
    val stamina : Int,
    val attack : Int,
    val defense : Int,
    val pokemonClass : String?,
    val skillQuick : List<EntitySkill>,
    val skillCinematic : List<EntitySkill>,
    val skillEliteQuick : List<EntitySkill>?,
    val skillEliteCinematic : List<EntitySkill>?,
    val imageNormal : String?,
    val imageShiny : String?,
    val imageCostume : Set<String>?,
    val evolutions : List<EntityEvolution>?,
    val megaEvolutions : List<EntityMegaEvolution>?
)

data class EntitySkill(
    val name : String,
    val type : String,
    val power : Int,
    val energy : Int,
    val durationMs : Int,
    val combatEnergy : Int,
    val combatPower : Int,
    val combatTurns : Int,
    val combatActivationChance : Int? = null,
    val combatAttackerAttackStatsChange : Int? = null,
    val combatAttackerDefenseStatsChange : Int? = null,
    val combatTargetAttackStatsChange : Int? = null,
    val combatTargetDefenseStatsChange : Int? = null
)

data class EntityEvolution(
    val id : String,
    val candies : Int,
    val item : String?,
    val questDescription : String?
)

data class EntityMegaEvolution(
    val name : String,
    val typePrimary : String,
    val typeSecondary : String?,
    val stamina : Int,
    val attack : Int,
    val defense : Int,
    val imageNormal : String?,
    val imageShiny : String?,
)