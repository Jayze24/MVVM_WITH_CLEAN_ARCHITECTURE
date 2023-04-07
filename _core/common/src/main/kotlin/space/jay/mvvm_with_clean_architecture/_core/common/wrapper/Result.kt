package space.jay.mvvm_with_clean_architecture._core.common.wrapper

sealed interface Result<out T>
data class Success<out T>(val data : T) : Result<T>
data class Fail(val throwable : Throwable) : Result<Nothing>

sealed interface NetworkError : Result<Nothing> {
    val code: Int
    val message : String
}
data class ServerError(override val code: Int, override val message: String) : NetworkError
data class ClientError(override val code: Int, override val message: String) : NetworkError