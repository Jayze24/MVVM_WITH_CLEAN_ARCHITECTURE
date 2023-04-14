package space.jay.mvvm_with_clean_architecture._feature.searchWiki

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import space.jay.mvvm_with_clean_architecture.core.common.log.Log
import space.jay.mvvm_with_clean_architecture.core.model.wiki.EntityWiki
import space.jay.mvvm_with_clean_architecture.core.ui.common.Loading
import space.jay.mvvm_with_clean_architecture.core.ui.common.NoData
import space.jay.mvvm_with_clean_architecture.core.ui.common.SearchBar
import space.jay.mvvm_with_clean_architecture._feature.searchWiki.state.StateUIWikiSearch

@Composable
fun ScreenSearchWiki(
    modifier : Modifier = Modifier,
    viewModel : ViewModelWikiSearch = hiltViewModel()
) {
    val stateUI by viewModel.stateUI.collectAsState()
    Log.send(stateUI)
    Column(modifier = modifier) {
        SearchBar(
            value = stateUI.searchInput,
            onValueChange = viewModel::search,
            hint = "search Wiki",
            isFocus = true,
            onSearch = { viewModel.search(stateUI.searchInput, true) }
        )
        SearchedWiki(stateUI)
    }
}

@Composable
fun SearchedWiki(stateUI : StateUIWikiSearch) {
    when (stateUI) {
        is StateUIWikiSearch.Loading -> Loading()
        is StateUIWikiSearch.HasData -> SearchedWikiHasData(data = stateUI.data)
        is StateUIWikiSearch.NoData -> NoData(message = "No Data")
    }
}

@Composable
fun SearchedWikiHasData(data : EntityWiki) {
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