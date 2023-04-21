package space.jay.mvvm_with_clean_architecture.feature.pokemon

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
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

fun NavGraphBuilder.toPokemon(
    window : TypeWindow,
    snackBar : SnackbarHostState
) {
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
                if (stateUIPokemonDetail.isVisible) {
                    BackHandler(onBack = viewModel::closePokemonDetail)
                    ScreenPokemonDetail(
                        stateUI = stateUIPokemonDetail,
                        snackBar = snackBar,
                        onClickRetry = viewModel::getPokemonDetail,
                        onDismissErrorMessage = viewModel::dismissedErrorPokemonDetail,
                    )
                } else {
                    ScreenListPokemon(
                        lazyListState = lazyListState,
                        stateUI = stateUIListPokemon,
                        snackBar = snackBar,
                        onClickSearch = viewModel::search,
                        onClickPokemon = viewModel::getPokemonDetail,
                        onClickRetry = viewModel::getListPokemon,
                        onDismissErrorMessage = viewModel::dismissedErrorListPokemon,
                    )
                }
            }
            TypePane.DUAL -> Row(modifier = Modifier.fillMaxSize()) {
                ScreenListPokemon(
                    modifier = Modifier.weight(1f),
                    lazyListState = lazyListState,
                    stateUI = stateUIListPokemon,
                    snackBar = snackBar,
                    onClickSearch = viewModel::search,
                    onClickPokemon = viewModel::getPokemonDetail,
                    onClickRetry = viewModel::getListPokemon,
                    onDismissErrorMessage = viewModel::dismissedErrorListPokemon,
                )

                ScreenPokemonDetail(
                    modifier = Modifier.weight(1f),
                    stateUI = stateUIPokemonDetail,
                    snackBar = snackBar,
                    onClickRetry = viewModel::getPokemonDetail,
                    onDismissErrorMessage = viewModel::dismissedErrorPokemonDetail,
                )
            }
        }
    }
}