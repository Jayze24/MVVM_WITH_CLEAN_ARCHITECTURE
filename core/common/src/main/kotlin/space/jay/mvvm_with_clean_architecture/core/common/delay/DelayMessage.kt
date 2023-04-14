package space.jay.mvvm_with_clean_architecture.core.common.delay

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.update
import java.util.*

class DelayMessage<T>(private val timeMillis : Long = 600) {

    private val state = MutableStateFlow(StateDelayMessage<T>())
    private val flowDelay : Flow<T> = state
        .debounce(timeMillis)
        .filter { !it.isDirect && it.message != null }
        .mapLatest { it.message!! }
    private val flowDirect : Flow<T> = state
        .filter { it.isDirect && it.message != null }
        .mapLatest { it.message!! }
    val flowResult = merge(flowDelay, flowDirect)

    fun setMessage(content : T, isDirect : Boolean = false) {
        state.update {
            it.copy(isDirect = isDirect, message = content, id = UUID.randomUUID().mostSignificantBits)
        }
    }
}