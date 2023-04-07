package space.jay.mvvm_with_clean_architecture.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import dagger.hilt.android.AndroidEntryPoint
import space.jay.mvvm_with_clean_architecture.ui.theme.BaseTheme
import space.jay.mvvm_with_clean_architecture.viewModel.UiStateWiki
import space.jay.mvvm_with_clean_architecture.viewModel.ViewModelWikiSearch
import space.jay.mvvm_with_clean_architecture.viewModel.ViewModelWikiSearch.Companion.QUERY

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    SearchWiki()
                    SearchedWiki()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWiki(
    viewModel : ViewModelWikiSearch = hiltViewModel()
) {
    val focus = remember { FocusRequester() }
    val query : String? by viewModel.savedStateHandle.getStateFlow(QUERY, null).collectAsState()
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focus),
        value = query ?: "",
        onValueChange = viewModel::search,
        label = { Text(text = "Search Wiki") },
        singleLine = true,
    )
    LaunchedEffect(key1 = true) {
        focus.requestFocus()
    }
}

@Composable
fun SearchedWiki(
    viewModel : ViewModelWikiSearch = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val wiki by viewModel.uiStateWiki.collectAsState()
    when {
        wiki.isLoading -> {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize(),
                text = "Loading...",
                textAlign = TextAlign.Center,
            )
        }
        wiki.data != null -> {
            val data = wiki.data!!
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp, 8.dp)
            ) {
                item { Image(image = data.image) }
                item { data.title?.also { Text(text = it, fontSize = 18.sp) } }
                item { data.description?.also { Text(text = it) } }
                data.listRelated?.also { list ->
                    items(list) { item ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(true) {
                                    viewModel.search(item.title)
                                },
                            text = item.title ?: "No Title"
                        )
                    }
                }
            }
        }
        wiki.errorMessage != null -> {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize(),
                text = wiki.errorMessage!!,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun Image(
    image : String?
) {
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