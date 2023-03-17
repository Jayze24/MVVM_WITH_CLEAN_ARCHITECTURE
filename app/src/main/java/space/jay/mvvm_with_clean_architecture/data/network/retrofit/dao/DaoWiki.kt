package space.jay.mvvm_with_clean_architecture.data.network.retrofit.dao

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import space.jay.mvvm_with_clean_architecture.data.network.retrofit.data.DataWiki
import space.jay.mvvm_with_clean_architecture.data.network.retrofit.data.ResponseListWik

interface DaoWiki {

    @GET("/api/rest_v1/page/related/{query}")
    suspend fun getListWikiRelated(@Path("query") query : String) : Response<ResponseListWik>

    @GET("/api/rest_v1/page/summary/{query}")
    suspend fun getWikiSummary(@Path("query") query : String) : Response<DataWiki>

}