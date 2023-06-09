package space.jay.mvvm_with_clean_architecture.core.common.wrapper

import java.util.*

data class ErrorMessage(
    val id : Long = UUID.randomUUID().mostSignificantBits,
    val message : String
)
