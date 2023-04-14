package space.jay.mvvm_with_clean_architecture._core.network

import android.content.Context
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.File

class TestRetrofitNetworkPokemonGo {

    // @Rule
    // @JvmField
    // val instantExecutorRule = InstantTaskExecutorRule()

    // private val context = ApplicationProvider.getApplicationContext<Context>()

    // private lateinit var server : MockWebServer
    // private lateinit var mockUrl : HttpUrl
    // private lateinit var testService : DaoPokemonGo // todo hilt로 변경
    // private lateinit var retrofitNetworkPokemonGo : RetrofitNetworkPokemonGo
    //
    // @Before
    // fun setUp() {
    //     server = MockWebServer()
    //     server.start()
    //     mockUrl = server.url("/")
    //
    //     testService = Retrofit.Builder()
    //         .addConverterFactory(GsonConverterFactory.create())
    //         .baseUrl(mockUrl)
    //         .build()
    //         .create(DaoPokemonGo::class.java)
    //
    //     retrofitNetworkPokemonGo = RetrofitNetworkPokemonGo(context, testService)
    // }

    @Test
    fun getListPokemon_success() = runBlocking {
        // server.enqueue(
        //     MockResponse().apply {
        //         setResponseCode(HttpURLConnection.HTTP_OK)
        //         setBody(fileReader(context, "pokedex.json"))
        //     }
        // )
        //
        // val result = retrofitNetworkPokemonGo.getListPokemon()
        // // assertThat(result).isInstanceOf(Success::class.java)
        // if (result is Success) {
        //     val pokedexId1 = fileReader(context, "pokedex_id_1.json")
        //     val pokemon = Gson().fromJson(pokedexId1, EntityPokemon::class.java)
        //     assertThat(result.data).contains(pokemon)
        // }

    }

    private fun fileReader(context : Context, fileName : String) : String {
        val file = File(context.classLoader.getResource(fileName).file)
        return file.bufferedReader().use { it.readText() }
    }
}