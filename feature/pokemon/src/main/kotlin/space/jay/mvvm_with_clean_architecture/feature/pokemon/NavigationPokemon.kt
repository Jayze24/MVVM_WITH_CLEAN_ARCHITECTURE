package space.jay.mvvm_with_clean_architecture.feature.pokemon

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import space.jay.mvvm_with_clean_architecture.core.common.window.TypePane
import space.jay.mvvm_with_clean_architecture.core.common.window.TypeWindow
import space.jay.mvvm_with_clean_architecture.feature.pokemon.listPokemon.ScreenListPokemon
import space.jay.mvvm_with_clean_architecture.feature.pokemon.pokemonDetail.ScreenPokemonDetail

const val routePokemon = "routePokemon"
const val argIsMega = "argIsMega"
const val routeFinalPokemon = "$routePokemon/{$argIsMega}"

fun NavController.navigateToPokemon(navOptions : NavOptions? = null, isMega : Boolean = false) {
    this.navigate("$routePokemon/$isMega", navOptions)
}

fun NavGraphBuilder.toPokemon(window : TypeWindow) {
    composable(
        route = routeFinalPokemon,
        arguments = listOf(
            navArgument(argIsMega) { type = NavType.BoolType }
        )
    ) {
        val viewModel : ViewModelPokemon = hiltViewModel()
        val stateUIListPokemon by viewModel.stateUIListPokemon.collectAsState()
        val stateUIPokemonDetail by viewModel.stateUIPokemonDetail.collectAsState()
        val lazyListState = rememberLazyListState()

        when (window.pane) {
            TypePane.SINGLE -> {
                if (stateUIPokemonDetail.isOpen) {
                    BackHandler(onBack = viewModel::closePokemonDetail)
                    ScreenPokemonDetail(stateUI = stateUIPokemonDetail)    
                } else {
                    ScreenListPokemon(
                        lazyListState = lazyListState,
                        stateUIListPokemon = stateUIListPokemon,
                        onClickSearch = viewModel::search,
                        onClickPokemon = viewModel::getPokemonDetail
                    )
                }
            }
            TypePane.DUAL -> Row(modifier = Modifier.fillMaxSize()) {
                ScreenListPokemon(
                    modifier = Modifier.weight(1f),
                    lazyListState = lazyListState,
                    stateUIListPokemon = stateUIListPokemon,
                    onClickSearch = viewModel::search,
                    onClickPokemon = viewModel::getPokemonDetail
                )
                ScreenPokemonDetail(modifier = Modifier.weight(1f), stateUI = stateUIPokemonDetail)
            }
        }
    }
}