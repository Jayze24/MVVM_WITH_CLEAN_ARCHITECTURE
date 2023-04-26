package space.jay.mvvm_with_clean_architecture.core.network.retrofit

import retrofit2.Response
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ClientError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Result
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ServerError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Fail

abstract class BaseRetrofitNetwork {

    suspend fun <I, O> callApi(
        api : suspend () -> Response<I>,
        mapping : (Response<I>) -> O
    ) : Result<O> {
        return try {
            api().let { result ->
                when (result.code()) {
                    in 200 until 300 -> Success(mapping(result))
                    in 400 until 500 -> ClientError(result.code(), result.message())
                    in 500 until 600-> ServerError(result.code(), result.message())
                    else -> Fail(Throwable("Unknown Error - ${result.code()}\n${result.errorBody().toString()}"))
                }
            }
        } catch (t : Throwable) {
            Fail(t)
        }
    }
}