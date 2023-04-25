package space.jay.mvvm_with_clean_architecture.core.ui.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TestNoData {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun noData() {
        val message = "no data"
        composeTestRule.setContent {
            NoData(message = message)
        }

        composeTestRule.onNodeWithText(message).assertIsDisplayed()
    }
}