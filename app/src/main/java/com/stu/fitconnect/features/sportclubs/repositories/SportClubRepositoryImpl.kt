package com.stu.fitconnect.features.sportclubs.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClub
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubSummary
import com.stu.fitconnect.features.sportclubs.domain.entity.SportClubsFiltersData
import com.stu.fitconnect.network.authentication.AuthenticationSource
import com.stu.fitconnect.network.sportclub.source.SportClubSource
import com.stu.fitconnect.network.sportclub.source.SportClubsPagingSource
import com.stu.fitconnect.utils.ResourceJsonManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

typealias SportClubsPageLoader = suspend (pageIndex: Int) -> List<SportClubSummary>

@Singleton
class SportClubRepositoryImpl @Inject constructor(
    private val sportClubSource: SportClubSource,
    private val authenticationSource: AuthenticationSource,
    private val resourceJsonManager: ResourceJsonManager
) : SportClubRepository {

    override suspend fun getSportClubById(id: Int): SportClub {
        val userId = authenticationSource.getCurrentUId()
        val sportClubsResponse = sportClubSource.getSportClubFullInfo(id, userId)
        val amenities = resourceJsonManager.getAllAmenities()
        return sportClubsResponse.toSportClub(amenities)
    }

    override fun getSportClubsPagingList(searchBy: String, sportClubsFilters: SportClubsFiltersData): Flow<PagingData<SportClubSummary>> {
        val userId = authenticationSource.getCurrentUId()

        val loader: SportClubsPageLoader = { pageIndex ->
            sportClubSource.getSummarySportClubsPagingList(
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


    private companion object {
        const val PAGE_SIZE = 6
    }
}