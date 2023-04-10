package space.jay.mvvm_with_clean_architecture._core.model

import java.util.*

data class ErrorMessage(
    val id : Long = UUID.randomUUID().mostSignificantBits,
    val message : String
)
