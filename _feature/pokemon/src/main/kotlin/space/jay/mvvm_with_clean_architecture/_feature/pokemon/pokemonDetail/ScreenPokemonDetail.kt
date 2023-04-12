package space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import space.jay.mvvm_with_clean_architecture._core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture._core.ui.common.Loading
import space.jay.mvvm_with_clean_architecture._core.ui.common.NoData
import space.jay.mvvm_with_clean_architecture._feature.pokemon.pokemonDetail.state.StateUIPokemonDetail

@Composable
fun ScreenPokemonDetail(
    modifier : Modifier = Modifier,
    viewModel : ViewModelPokemonDetail = hiltViewModel(),
    pokemonNumber : Int
) {
    val stateUI by viewModel.stateUI.collectAsState()
    LaunchedEffect(key1 = pokemonNumber, block = { viewModel.getPokemonDetail(pokemonNumber) })
    Column(modifier = modifier) {
        Content(stateUI = stateUI)
    }
}

@Composable
fun Content(stateUI : StateUIPokemonDetail) {
    when (stateUI) {
        is StateUIPokemonDetail.Loading -> Loading()
        is StateUIPokemonDetail.HasData -> HasData(stateUI.data)
        is StateUIPokemonDetail.NoData -> NoData(message = "포켓몬을 선택해 주세요.")
    }
}

@Composable
fun HasData(data : EntityPokemon) {
    BasicInfo(
        number = data.number,
        name = data.name,
        typePrimary = data.typePrimary,
        typeSecondary = data.typeSecondary,
        image = data.imageNormal,
    )
}

@Composable
fun BasicInfo(number : Int, name : String, typePrimary : String, typeSecondary : String?, image : String?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "No.$number")
            Text(text = name)
            Text(text = "$typePrimary ${typeSecondary ?: ""}")
        }
        AsyncImage(modifier = Modifier.size(120.dp, 180.dp), model = image, contentDescription = null)
    }
}