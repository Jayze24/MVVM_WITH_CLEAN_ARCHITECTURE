package space.jay.mvvm_with_clean_architecture._core.network.retrofit.di

import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.jay.mvvm_with_clean_architecture._core.network.SourcePokemonGo
import space.jay.mvvm_with_clean_architecture._core.network.SourceWiki
import space.jay.mvvm_with_clean_architecture._core.network.retrofit.RetrofitNetworkPokemonGo
import space.jay.mvvm_with_clean_architecture._core.network.retrofit.RetrofitNetworkWiki
import space.jay.mvvm_with_clean_architecture._core.network.retrofit.dao.DaoPokemonGo
import space.jay.mvvm_with_clean_architecture._core.network.retrofit.dao.DaoWiki
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HiltRetrofit {

    @Binds
    fun RetrofitNetworkWiki.bindWiki(): SourceWiki

    @Binds
    fun RetrofitNetworkPokemonGo.bindPokemonGo(): SourcePokemonGo
}

@Module
@InstallIn(SingletonComponent::class)
internal object HiltRetrofitFactory {

    @Provides
    @Singleton
    internal fun providesOkHttpCallFactory() : Call.Factory =
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                },
            )
            .build()

    @Provides
    @Singleton
    internal fun providesWiki(okHttpCallFactory : Call.Factory) : DaoWiki =
        Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org")
            .callFactory(okHttpCallFactory)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(DaoWiki::class.java)


    @Provides
    @Singleton
    internal fun providesPokemonGo(okHttpCallFactory : Call.Factory) : DaoPokemonGo =
        Retrofit.Builder()
            .baseUrl("https://pokemon-go-api.github.io")
            .callFactory(okHttpCallFactory)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(DaoPokemonGo::class.java)
}
