package space.jay.mvvm_with_clean_architecture.data.network.retrofit.dao

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SettingDao {

    fun <T> initApi(baseUrl : String, service : Class<T>) : T {
        val httpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()
            .create(service)
    }

}