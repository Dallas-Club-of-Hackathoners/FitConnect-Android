package com.stu.fitconnect.features.sportsclubs.presentation

import com.stu.fitconnect.features.sportsclubs.domain.ClubsCategory
import com.stu.fitconnect.features.sportsclubs.domain.Cost
import com.stu.fitconnect.features.sportsclubs.domain.Facility
import com.stu.fitconnect.features.sportsclubs.domain.IsFavouriteFilter
import com.stu.fitconnect.features.sportsclubs.domain.SortType

data class AllSportsClubFiltersData(
    val isFavourite: IsFavouriteFilter,
    val facilities: List<Facility>,
    val cost: List<Cost>,
    val clubsCategory: List<ClubsCategory>,
    val sortTypes: List<SortType>
) //todo переписать json
