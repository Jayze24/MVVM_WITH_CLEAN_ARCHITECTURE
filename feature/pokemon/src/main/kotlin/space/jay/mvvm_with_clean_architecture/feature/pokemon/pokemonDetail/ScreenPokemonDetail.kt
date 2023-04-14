package space.jay.mvvm_with_clean_architecture.feature.pokemon.pokemonDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import space.jay.mvvm_with_clean_architecture.core.model.pokemon.EntityPokemon
import space.jay.mvvm_with_clean_architecture.core.ui.common.Loading
import space.jay.mvvm_with_clean_architecture.core.ui.common.NoData

@Composable
fun ScreenPokemonDetail(
    modifier : Modifier = Modifier,
    stateUI : StateUIPokemonDetail
) {
    Column(modifier = modifier) {
        when (stateUI) {
            is StateUIPokemonDetail.Loading -> Loading()
            is StateUIPokemonDetail.HasData -> HasData(stateUI.data)
            is StateUIPokemonDetail.NoData -> NoData(message = "포켓몬을 선택해 주세요.")
        }
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
    // todo jay 정보 추가하기....
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