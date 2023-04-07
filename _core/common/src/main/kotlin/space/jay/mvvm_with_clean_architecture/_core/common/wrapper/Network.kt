package space.jay.mvvm_with_clean_architecture._core.common.wrapper

sealed interface NetworkError
data class ServerError(val code: Int, val message: String) : NetworkError, NetworkResult<Nothing>
data class ClientError(val code: Int, val message: String) : NetworkError, NetworkResult<Nothing>

sealed interface NetworkResult<out T>
data class Success<out T>(val data : T) : NetworkResult<T>
data class Fail(val throwable : Throwable) : NetworkResult<Nothing>