package space.jay.mvvm_with_clean_architecture.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import space.jay.mvvm_with_clean_architecture.core.common.window.Feature
import space.jay.mvvm_with_clean_architecture.ui.theme.BaseTheme
import java.lang.ref.WeakReference

@AndroidEntryPoint
class ActivityMain : ComponentActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = contentActivity(WeakReference(this)))
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
private fun contentActivity(weakActivity : WeakReference<Activity>) : @Composable () -> Unit = {
    val typeWindow = Feature.getTypeWindow(weakActivity = weakActivity)
    val navController = rememberNavController()
    val snackBarHostState : SnackbarHostState = remember { SnackbarHostState() }
    BaseTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
        ) { padding ->
            Surface(
                modifier = Modifier
                    .padding(padding)
                    .consumeWindowInsets(paddingValues = padding)
            ) {
                NavHostMain(navController = navController, typeWindow = typeWindow, snackBar = snackBarHostState)
            }
        }
    }
}

