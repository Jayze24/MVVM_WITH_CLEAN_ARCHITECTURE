package space.jay.mvvm_with_clean_architecture.core.network

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ClientError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ServerError
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.Success
import space.jay.mvvm_with_clean_architecture.core.network.retrofit.RetrofitNetworkPokemonGo
import space.jay.mvvm_with_clean_architecture.core.network.retrofit.dao.DaoPokemonGo
import space.jay.mvvm_with_clean_architecture.core.test.fakeData.FakeDataPokemon
import java.net.HttpURLConnection

@RunWith(RobolectricTestRunner::class)
class TestRetrofitNetworkPokemonGo {

    private val context : Context = ApplicationProvider.getApplicationContext<Context>()
    private val fakeDataPokemon : FakeDataPokemon = FakeDataPokemon(context)
    private lateinit var server : MockWebServer
    private lateinit var retrofitNetworkPokemonGo : RetrofitNetworkPokemonGo

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        val daoPokemonGo = Retrofit.Builder()
            .baseUrl("http://localhost:${server.port}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DaoPokemonGo::class.java)
        retrofitNetworkPokemonGo = RetrofitNetworkPokemonGo(context, daoPokemonGo)
    }

    @After
    fun close() {
        server.shutdown()
    }

    @Test
    fun getListPokemon_success() = runBlocking {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_OK)
                addHeader("Content-Type", "application/json")
                setBody(fakeDataPokemon.getJsonListDataPokemon())
            }
        )

        val result = retrofitNetworkPokemonGo.getListPokemon()
        // 성공 wrapper 왔는지 확인
        assertThat(result).isInstanceOf(Success::class.java)
        if (result is Success) {
            // 데이터가 entity로 변경되었는지 확인
            assertThat(result.data[0]).isEqualTo(fakeDataPokemon.getEntityPokemonId1())
        }
    }

    @Test
    fun getListPokemon_Error_Client() = runBlocking {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            }
        )

        val result = retrofitNetworkPokemonGo.getListPokemon()
        // 에러(클라이언트) 왔는지 확인
        assertThat(result).isInstanceOf(ClientError::class.java)
        if (result is ClientError) {
            // 코드 확인
            assertThat(result.code).isIn(400 until 500)
            assertThat(result.message).isNotNull()
        }
    }

    @Test
    fun getListPokemon_Error_Server() = runBlocking {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
            }
        )

        val result = retrofitNetworkPokemonGo.getListPokemon()
        // 에러(서버) 왔는지 확인
        assertThat(result).isInstanceOf(ServerError::class.java)
        if (result is ServerError) {
            // 코드 확인
            assertThat(result.code).isAtLeast(500)
            assertThat(result.message).isNotNull()
        }
    }

    @Test
    fun getListMegaPokemon_success() = runBlocking {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_OK)
                addHeader("Content-Type", "application/json")
                setBody(fakeDataPokemon.getJsonListMegaDataPokemon())
            }
        )

        val result = retrofitNetworkPokemonGo.getListMegaPokemon()
        // 성공 wrapper 왔는지 확인
        assertThat(result).isInstanceOf(Success::class.java)
        if (result is Success) {
            // 데이터가 entity로 변경되었는지 확인
            assertThat(result.data[0]).isEqualTo(fakeDataPokemon.getEntityPokemonId3())
        }
    }

    @Test
    fun getListPokemonByGeneration_success() = runBlocking {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_OK)
                addHeader("Content-Type", "application/json")
                setBody(fakeDataPokemon.getJsonListGeneration9DataPokemon())
            }
        )

        val result = retrofitNetworkPokemonGo.getListPokemonByGeneration(1)
        // 성공 wrapper 왔는지 확인
        assertThat(result).isInstanceOf(Success::class.java)
        if (result is Success) {
            // 데이터가 entity로 변경되었는지 확인
            assertThat(result.data[0]).isEqualTo(fakeDataPokemon.getEntityPokemonId901())
        }
    }

    @Test
    fun getPokemonByNumber_success() = runBlocking {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_OK)
                addHeader("Content-Type", "application/json")
                setBody(fakeDataPokemon.getJsonDataPokemonId1())
            }
        )

        val result = retrofitNetworkPokemonGo.getPokemonByNumber(1)
        // 성공 wrapper 왔는지 확인
        assertThat(result).isInstanceOf(Success::class.java)
        if (result is Success) {
            // 데이터가 entity로 변경되었는지 확인
            assertThat(result.data).isEqualTo(fakeDataPokemon.getEntityPokemonId1())
        }
    }

    @Test
    fun getPokemonByName_success() = runBlocking {
        server.enqueue(
            MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_OK)
                addHeader("Content-Type", "application/json")
                setBody(fakeDataPokemon.getJsonDataPokemonId1())
            }
        )

        val result = retrofitNetworkPokemonGo.getPokemonByName("BULBASAUR")
        // 성공 wrapper 왔는지 확인
        assertThat(result).isInstanceOf(Success::class.java)
        if (result is Success) {
            // 데이터가 entity로 변경되었는지 확인
            assertThat(result.data).isEqualTo(fakeDataPokemon.getEntityPokemonId1())
        }
    }
}