package space.jay.mvvm_with_clean_architecture._feature.searchWiki

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import space.jay.mvvm_with_clean_architecture._feature.searchWiki.ViewModelWikiSearch.Companion.QUERY

@Composable
fun ScreenSearchWiki(
    modifier : Modifier = Modifier,
    viewModel : ViewModelWikiSearch = hiltViewModel()
) {
    val query : String? by viewModel.savedStateHandle.getStateFlow(QUERY, null).collectAsState()
    val uiStateWiki by viewModel.uiStateWiki.collectAsState()
    Column(modifier = modifier) {
        SearchBarWiki(
            value = query ?: "",
            onValueChange = viewModel::search
        )
        SearchedWiki(uiStateWiki)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWiki(
    value: String,
    onValueChange: (String) -> Unit,
) {
    val focus = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focus),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "Search Wiki") },
        singleLine = true,
    )
    LaunchedEffect(key1 = true) {
        focus.requestFocus()
    }
}

@Composable
fun SearchedWiki(uiStateWiki : UiStateWiki) {
    when {
        uiStateWiki.isLoading -> {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize(),
                text = "Loading...",
                textAlign = TextAlign.Center,
            )
        }
        uiStateWiki.data != null -> {
            val data = uiStateWiki.data!!
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp, 8.dp)
            ) {
                item { Image(image = data.image) }
                item { data.title?.also { Text(text = it, fontSize = 18.sp) } }
                item { data.description?.also { Text(text = it) } }
            }
        }
        uiStateWiki.errorMessage != null -> {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize(),
                text = uiStateWiki.errorMessage!!,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun Image(image : String?) {
    image?.also {
        AsyncImage(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            model = it,
            contentDescription = null
        )
    }
}