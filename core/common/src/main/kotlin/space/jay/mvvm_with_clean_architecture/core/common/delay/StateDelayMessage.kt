package space.jay.mvvm_with_clean_architecture.core.common.delay

import java.util.UUID

internal data class StateDelayMessage<T>(
    val isDirect : Boolean = true,
    val message : T? = null,
    val id : Long = UUID.randomUUID().mostSignificantBits
)
