package com.harissabil.glogslite.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.harissabil.glogslite.BuildConfig
import com.harissabil.glogslite.data.remote.api.ApiService
import com.harissabil.glogslite.data.remote.response.ResultsItem

const val NETWORK_PAGE_SIZE = 15
private const val INITIAL_LOAD_SIZE = 1

class GamePagingSource(private val platform: Int, private val apiService: ApiService) :
    PagingSource<Int, ResultsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        return try {
            val response = apiService.getGames(
                apiKey = BuildConfig.API_KEY,
                offset = offset,
                limit = params.loadSize,
                platform = platform
            )
            val nextKey = if (response.numberOfTotalResults == null) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == INITIAL_LOAD_SIZE) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}