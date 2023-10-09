package com.stu.fitconnect.network.spotclubs.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepository
import com.stu.fitconnect.features.sportclubs.domain.SportClub
import com.stu.fitconnect.features.sportclubs.domain.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.SportClubsFiltersData
import com.stu.fitconnect.network.authentication.AuthenticationSource
import com.stu.fitconnect.network.spotclubs.source.SportClubsPagingSource
import com.stu.fitconnect.network.spotclubs.source.SportClubsSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


typealias SportClubsPageLoader = suspend (pageIndex: Int) -> List<SportClubSummary>

@Singleton
class SportClubsRepositoryImpl @Inject constructor(
    private val sportClubsSource: SportClubsSource,
    private val authenticationSource: AuthenticationSource
) : SportClubsRepository {

    override fun getSportClubsPagingList(searchBy: String, sportClubsFilters: SportClubsFiltersData): Flow<PagingData<SportClubSummary>> {
        val userId = authenticationSource.getCurrentUId()

        val loader: SportClubsPageLoader = { pageIndex ->
            sportClubsSource.getSummarySportClubsPagingList(
                filters = sportClubsFilters,
                searchBy = searchBy,
                pageIndex = pageIndex,
                userId = userId
            )
        }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SportClubsPagingSource(loader)
            }
        ).flow
    }

    override fun getSportClubById(id: Int): SportClub {
        TODO("Not yet implemented")
    }

    private companion object {
        const val PAGE_SIZE = 6
    }
}