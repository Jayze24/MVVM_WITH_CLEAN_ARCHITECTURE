package space.jay.mvvm_with_clean_architecture.ui.activity

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import space.jay.mvvm_with_clean_architecture.core.common.window.TypeWindow
import space.jay.mvvm_with_clean_architecture.feature.pokemon.routeFinalPokemon
import space.jay.mvvm_with_clean_architecture.feature.pokemon.toPokemon

@Composable
fun NavHostMain(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    screenStart: String = routeFinalPokemon,
    typeWindow : TypeWindow,
    snackBar : SnackbarHostState
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = screenStart
    ) {
        toPokemon(typeWindow, snackBar)
    }
}