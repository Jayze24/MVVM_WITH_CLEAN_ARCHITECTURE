package space.jay.mvvm_with_clean_architecture.feature.pokemon.listPokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture.core.ui.common.Loading
import space.jay.mvvm_with_clean_architecture.core.ui.common.NoData
import space.jay.mvvm_with_clean_architecture.core.ui.common.SearchBar
import space.jay.mvvm_with_clean_architecture.core.ui.common.ShowErrorMessage

@Composable
fun ScreenListPokemon(
    modifier : Modifier = Modifier,
    lazyListState : LazyListState,
    stateUI : StateUIListPokemon,
    snackBar: SnackbarHostState,
    onClickSearch: (name : String) -> Unit,
    onClickPokemon : (number : Int) -> Unit,
    onClickRetry : () -> Unit,
    onDismissErrorMessage : (id : Long) -> Unit
) {

    Column(modifier = modifier) {
        SearchBar(
            value = stateUI.searchInput,
            onValueChange = onClickSearch,
            hint = "search Pokemon",
        )
        when (stateUI) {
            is StateUIListPokemon.Loading -> Loading()
            is StateUIListPokemon.HasData -> HasData(listPokemon = stateUI.listData, state = lazyListState, onClickPokemon)
            is StateUIListPokemon.NoData -> NoData(message = "No Data")
        }
    }

    ShowErrorMessage(
        snackBar = snackBar,
        listErrorMessage = stateUI.errorMessage,
        onClickRetry = onClickRetry,
        onDismissErrorMessage = onDismissErrorMessage
    )
}

@Composable
fun HasData(listPokemon : List<EntityPokemon>, state : LazyListState, onClick : (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp),
        state = state
    ) {
        items(listPokemon) { pokemon ->
            HolderPokemon(data = pokemon, onClick)
        }
    }
}

@Composable
fun HolderPokemon(data : EntityPokemon, onClick : (Int) -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(data.number) })
            .padding(8.dp),
        text = "No.${data.number}\n${data.name}"
    )
}