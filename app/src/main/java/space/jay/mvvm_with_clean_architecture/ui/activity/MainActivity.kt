package space.jay.mvvm_with_clean_architecture.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import space.jay.mvvm_with_clean_architecture.core.common.window.Feature
import space.jay.mvvm_with_clean_architecture._feature.pokemon.routeFinalPokemon
import space.jay.mvvm_with_clean_architecture._feature.pokemon.toPokemon
import space.jay.mvvm_with_clean_architecture.ui.theme.BaseTheme
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                // todo jay 네비게이션 제대로 붙이기
                val nav = rememberNavController()
                val feature = Feature.getFeature(weakActivity = WeakReference(this))
                NavHost(
                    navController = nav,
                    startDestination = routeFinalPokemon
                ) {
                    toPokemon(feature)
                }
            }
        }
    }
}

