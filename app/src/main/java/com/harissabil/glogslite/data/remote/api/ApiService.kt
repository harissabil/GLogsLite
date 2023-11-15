package com.harissabil.glogslite.data.remote.api

import com.harissabil.glogslite.data.remote.response.DetailResponse
import com.harissabil.glogslite.data.remote.response.GamesResponse
import com.harissabil.glogslite.data.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json",
        @Query("field_list") field_list: String = "guid,name,image,platforms,original_release_date",
        @Query("sort") sort: String = "id:desc",
        @Query("platforms") platform: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): GamesResponse

    @GET("search")
    suspend fun getSearchGame(
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json",
        @Query("query") query: String,
        @Query("resources") resources: String = "game"
    ): SearchResponse

    @GET("game/{guid}")
    suspend fun getDetailGame(
        @Path("guid") guid: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json",
    ): DetailResponse
}