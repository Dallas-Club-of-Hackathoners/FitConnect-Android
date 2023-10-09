package com.stu.fitconnect.features.sportactivities.domain



//import com.stu.fitconnect.features.sportsclubs.domain.ClubsCategory
//import com.stu.fitconnect.features.sportsclubs.domain.Trainer
//
//data class SportClubActivityFilters(
//    val isFavorite: Boolean = false,
//    val clubsCategory: List<ClubsCategory> = emptyList(),
//    val trainers: List<Trainer> = emptyList(),
//    val time: List<String> = emptyList()
//)

data class SportClubActivityFilters(
    var filterList: List<FilterCategoryActivities> = emptyList()
)

data class FilterCategoryActivities (
    val id: Int,
    val name: String,
    val filterList: List<FilterActivities>
)

data class SortType(
    val id: Int = 0,
    val name: String,
)

data class FilterActivities (
    val id:Int,
    val name: String,
    val categoryId:Int,
    val imageRes:Int? = null,
    val time: String? = null,
    var isSelected: Boolean = false
)

/*
использовать этот филтр для всех????

data class Filter(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val imageRes: Int? = null,
    val count: Int? = null,
    var isSelect: Boolean = false
)

 */

