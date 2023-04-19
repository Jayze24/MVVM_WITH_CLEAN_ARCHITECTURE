package space.jay.mvvm_with_clean_architecture.core.common.window

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import com.google.accompanist.adaptive.calculateDisplayFeatures
import java.lang.ref.WeakReference

object Feature {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    fun getTypeWindow(weakActivity : WeakReference<Activity>) : TypeWindow {
        return weakActivity.get()?.let { activity ->
            val windowSize = calculateWindowSizeClass(activity)
            // todo jay displayFeatures 활용해서 더 세분화 할 것
            val displayFeatures = calculateDisplayFeatures(activity)

            when (windowSize.widthSizeClass) {
                WindowWidthSizeClass.Medium -> TypeWindow(TypePane.DUAL, TypeOrientation.LANDSCAPE)
                WindowWidthSizeClass.Expanded -> TypeWindow(TypePane.DUAL, TypeOrientation.LANDSCAPE_WIDE)
                else -> TypeWindow(TypePane.SINGLE, TypeOrientation.PORTRAIT)
            }
        } ?: TypeWindow(TypePane.SINGLE, TypeOrientation.PORTRAIT)
    }
}