package space.jay.mvvm_with_clean_architecture.core.common.delay

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.update
import java.util.UUID

class DelayMessage<T : Any>(timeMillis : Long = 600) {

    private val state = MutableStateFlow(StateDelayMessage<T>())
    @OptIn(FlowPreview::class)
    private val flowDelay : Flow<T> = state
        .debounce(timeMillis)
        .filter { !it.isDirect && it.message != null }
        .map { it.message!! }
    private val flowDirect : Flow<T> = state
        .filter { it.isDirect && it.message != null }
        .map { it.message!! }
    val flowResult = merge(flowDelay, flowDirect)

    fun setMessage(content : T, isDirect : Boolean = false) {
        state.update {
            it.copy(isDirect = isDirect, message = content, id = UUID.randomUUID().mostSignificantBits)
        }
    }
}