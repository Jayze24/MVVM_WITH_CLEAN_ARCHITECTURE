package space.jay.mvvm_with_clean_architecture._feature.pokemon

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import space.jay.mvvm_with_clean_architecture._core.common.window.TypePane
import space.jay.mvvm_with_clean_architecture._core.common.window.TypeWindow
import space.jay.mvvm_with_clean_architecture._feature.pokemon.listPokemon.ScreenListPokemon
import space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail.ScreenPokemonDetail

const val routeListPokemon = "routeListPokemon"
const val argIsMega = "argIsMega"
const val routeFinalListPokemon = "$routeListPokemon/{$argIsMega}"

fun NavController.navigateToListPokemon(navOptions : NavOptions? = null, isMega : Boolean = false) {
    this.navigate("$routeListPokemon/$isMega", navOptions)
}

fun NavGraphBuilder.toListPokemon(window : TypeWindow) {
    composable(
        route = routeFinalListPokemon,
        arguments = listOf(
            navArgument(argIsMega) { type = NavType.BoolType }
        )
    ) {
        when (window.pane) {
            TypePane.SINGLE -> ScreenListPokemon()
            TypePane.DUAL ->  Row(modifier = Modifier.fillMaxSize()) {
                ScreenListPokemon(modifier = Modifier.weight(1f))
                ScreenPokemonDetail(modifier = Modifier.weight(1f), pokemonNumber = 0)
            }
        }
    }
}

const val routePokemonDetail = "routePokemonDetail"
const val argNumber = "argNumber"
const val routeFinalPokemonDetail = "$routePokemonDetail/{$argNumber}"

fun NavController.navigateToPokemonDetail(navOptions : NavOptions? = null, number : Int = 0) {
    this.navigate("$routePokemonDetail/$number", navOptions)
}

fun NavGraphBuilder.toPokemonDetail(window : TypeWindow) {
    composable(
        route = routeFinalPokemonDetail,
        arguments = listOf(
            navArgument(argNumber) { type = NavType.IntType }
        )
    ) {
        val number = it.arguments?.getInt(argNumber) ?: 0
        when (window.pane) {
            TypePane.SINGLE -> ScreenPokemonDetail(pokemonNumber = number)
            TypePane.DUAL ->  Row(modifier = Modifier.fillMaxSize()) {
                ScreenListPokemon(modifier = Modifier.weight(1f))
                ScreenPokemonDetail(modifier = Modifier.weight(1f), pokemonNumber = number)
            }
        }
    }
}

fun NavGraphBuilder.toPokemon(window : TypeWindow) {
    // todo jay 화면 회전할때 새로운 화면이 만들어짐. 수정할 것!
    toListPokemon(window)
    toPokemonDetail(window)
}