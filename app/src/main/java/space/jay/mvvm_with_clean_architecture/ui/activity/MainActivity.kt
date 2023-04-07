package space.jay.mvvm_with_clean_architecture.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import space.jay.mvvm_with_clean_architecture._feature.searchWiki.ScreenSearchWiki
import space.jay.mvvm_with_clean_architecture.ui.theme.BaseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                // todo jay 나중에 네비게이션 적용하기
                ScreenSearchWiki()
            }
        }
    }
}

