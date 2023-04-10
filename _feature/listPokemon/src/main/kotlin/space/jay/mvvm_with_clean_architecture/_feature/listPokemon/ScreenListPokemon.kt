package space.jay.mvvm_with_clean_architecture._feature.listPokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import space.jay.mvvm_with_clean_architecture._core.common.log.Log
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture._core.model.wiki.EntityWiki
import space.jay.mvvm_with_clean_architecture._core.ui.common.Loading
import space.jay.mvvm_with_clean_architecture._core.ui.common.NoData
import space.jay.mvvm_with_clean_architecture._core.ui.common.SearchBar
import space.jay.mvvm_with_clean_architecture._feature.listPokemon.state.StateUIListPokemon

@Composable
fun ScreenListPokemon(
    modifier : Modifier = Modifier,
    viewModel : ViewModelListPokemon = hiltViewModel()
) {
    val stateUI by viewModel.stateUI.collectAsState()
    Log.send(stateUI)
    Column(modifier = modifier) {
        SearchBar(
            value = stateUI.searchInput,
            onValueChange = viewModel::search,
            hint = "search Pokemon",
        )
        Content(stateUI = stateUI)
    }
}

@Composable
fun Content(stateUI : StateUIListPokemon) {
    when(stateUI) {
        is StateUIListPokemon.Loading -> Loading()
        is StateUIListPokemon.HasData -> HasData(listPokemon = stateUI.listData)
        is StateUIListPokemon.NoData -> NoData(message = "No Data")
    }
}

@Composable
fun HasData(listPokemon : List<EntityPokemon>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp)
    ) {
        items(listPokemon) { pokemon ->
            HolderPokemon(data = pokemon)
        }
    }
}

@Composable
fun HolderPokemon(data : EntityPokemon) {
    Text(text = "No.${data.number}\n${data.name}")
}