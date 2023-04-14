// package space.jay.mvvm_with_clean_architecture._feature.pokemon
//
// import androidx.compose.foundation.lazy.LazyListState
// import androidx.compose.foundation.lazy.rememberLazyListState
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.Stable
// import androidx.compose.runtime.rememberCoroutineScope
// import androidx.compose.runtime.saveable.rememberSaveable
// import kotlinx.coroutines.CoroutineScope
// import space.jay.mvvm_with_clean_architecture.core.common.window.TypeWindow
//
// @Composable
// fun rememberStateRoutePokemon(
//     typeWindow : TypeWindow,
//     coroutineScope: CoroutineScope = rememberCoroutineScope(),
//     lazyListState : LazyListState = rememberLazyListState()
// ) : StateRoutePokemon {
//     return rememberSaveable(typeWindow, coroutineScope, LazyListState.Saver) {
//         StateRoutePokemon(
//             typeWindow,
//             coroutineScope,
//             lazyListState
//         )
//     }
// }
//
// @Stable
// data class StateRoutePokemon(
//     val typeWindow : TypeWindow,
//     val coroutineScope: CoroutineScope,
//     val lazyListState : LazyListState
// )