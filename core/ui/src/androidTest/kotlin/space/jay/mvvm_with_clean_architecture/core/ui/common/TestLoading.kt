package space.jay.mvvm_with_clean_architecture.core.ui.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class TestLoading {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loading() {
        composeTestRule.setContent {
            Loading()
        }

        composeTestRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

}