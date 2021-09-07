package com.vinay.kotlin_coroutine_examples.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinay.kotlin_coroutine_examples.model.Movie
import com.vinay.kotlin_coroutine_examples.network.ApiHelper
import com.vinay.kotlin_coroutine_examples.network.ApiHelperInterface
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1


class MoviePagingSource(
    private val apiHelperInterface : ApiHelper,
    private val query :String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
//            val response = apiHelperInterface.getTopRatedMovies(query, position, params.loadSize)
            val response = apiHelperInterface.getTopRatedMovies(query, position, params.loadSize)
            val photos = response?.movies

            LoadResult.Page(
                data = photos!!,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
             LoadResult.Error(exception)
        }

    }


}