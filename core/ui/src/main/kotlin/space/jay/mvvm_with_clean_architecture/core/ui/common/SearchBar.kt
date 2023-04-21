package space.jay.mvvm_with_clean_architecture.core.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value : String,
    onValueChange : (String) -> Unit,
    hint : String,
    isFocus : Boolean = false,
    onSearch : (KeyboardActionScope.() -> Unit)? = null
) {
    // todo jay 포커스 임시로 이렇게 만듬. 나중에 포커스 관련 리펙토링 할 것
    val focus = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focus),
        value = value,
        onValueChange = onValueChange,
        keyboardActions = KeyboardActions(onSearch = onSearch),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        label = { Text(text = hint) },
        singleLine = true,
    )
    LaunchedEffect(key1 = true) {
        if (isFocus) focus.requestFocus()
    }
}