package space.jay.mvvm_with_clean_architecture.core.common.delay

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.util.UUID

@ExperimentalCoroutinesApi
@FlowPreview
class DelayMessage<T : Any>(timeMillis : Long = 600) {

    private val state = MutableStateFlow(StateDelayMessage<T>())
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