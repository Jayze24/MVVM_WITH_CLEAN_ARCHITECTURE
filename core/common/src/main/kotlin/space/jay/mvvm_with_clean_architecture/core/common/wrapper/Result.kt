package space.jay.mvvm_with_clean_architecture.core.common.wrapper

sealed interface Result<out T>
data class Success<out T>(val data : T) : Result<T>

sealed interface Fail : Result<Nothing> {
    val code : Int?
    val message : String?
}

data class Error(override val code : Int? = null, override val message : String? = null) : Fail

sealed interface NetworkError : Fail
data class ServerError(override val code : Int? = null, override val message : String? = null) : NetworkError
data class ClientError(override val code : Int? = null, override val message : String? = null) : NetworkError