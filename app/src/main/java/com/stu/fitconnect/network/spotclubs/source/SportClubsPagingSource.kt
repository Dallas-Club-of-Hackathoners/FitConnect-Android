package com.stu.fitconnect.network.spotclubs.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.network.spotclubs.repositories.SportClubsPageLoader

class SportClubsPagingSource(
    private val loader: SportClubsPageLoader
) : PagingSource<Int, SportClubSummary>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SportClubSummary> {
        val pageIndex = params.key ?: 0

        return try {
            val sportClubsList = loader.invoke(pageIndex)
            return LoadResult.Page(
                data = sportClubsList,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (sportClubsList.size == params.loadSize) pageIndex + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SportClubSummary>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}