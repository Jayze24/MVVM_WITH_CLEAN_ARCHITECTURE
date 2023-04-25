package space.jay.mvvm_with_clean_architecture.feature.pokemon

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import space.jay.mvvm_with_clean_architecture.core.test.fakeData.FakeDataPokemon
import space.jay.mvvm_with_clean_architecture.feature.pokemon.pokemonDetail.ScreenPokemonDetail
import space.jay.mvvm_with_clean_architecture.feature.pokemon.pokemonDetail.StateUIPokemonDetail

@RunWith(RobolectricTestRunner::class)
class TestPokemonDetail {

    @get:Rule
    val composeTestRule = createComposeRule() // use createAndroidComposeRule<YourActivity>() if you need access to an activity
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun screenPokemonDetail_isLoading() {
        composeTestRule.setContent {
            val fakeSnackBarHostState = remember { SnackbarHostState() }
            ScreenPokemonDetail(
                stateUI =   StateUIPokemonDetail.Loading(false, emptyList()),
                snackBar = fakeSnackBarHostState,
                onClickRetry = {},
                onDismissErrorMessage = {}
            )
        }
        
        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun screenPokemonDetail_NoData() {
        composeTestRule.setContent {
            val fakeSnackBarHostState = remember { SnackbarHostState() }
            ScreenPokemonDetail(
                stateUI =   StateUIPokemonDetail.NoData(false, emptyList()),
                snackBar = fakeSnackBarHostState,
                onClickRetry = {},
                onDismissErrorMessage = {}
            )
        }

        composeTestRule.onNodeWithText("포켓몬을 선택해 주세요.").assertIsDisplayed()
    }

    @Test
    fun screenPokemonDetail_HasData() {
        val fakeDataPokemon = FakeDataPokemon(context).getEntityPokemonId1()
        composeTestRule.setContent {
            val fakeSnackBarHostState = remember { SnackbarHostState() }

            ScreenPokemonDetail(
                stateUI =   StateUIPokemonDetail.HasData(
                    false,
                    emptyList(),
                    fakeDataPokemon
                ),
                snackBar = fakeSnackBarHostState,
                onClickRetry = {},
                onDismissErrorMessage = {}
            )
        }

        composeTestRule.onNodeWithText(fakeDataPokemon.name).assertIsDisplayed()
    }
}