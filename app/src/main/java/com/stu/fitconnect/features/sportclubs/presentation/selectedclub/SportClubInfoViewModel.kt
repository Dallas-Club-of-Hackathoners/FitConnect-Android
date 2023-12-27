package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.stu.fitconnect.R
import com.stu.fitconnect.base.ResourceManager
import com.stu.fitconnect.features.sportclubs.domain.entity.AmenityWithAvailable
import com.stu.fitconnect.features.sportclubs.domain.entity.FilterCategory
import com.stu.fitconnect.features.sportclubs.domain.usecases.GetSportClubInfoUseCase
import com.stu.fitconnect.features.sportclubs.presentation.list.SportClubListContract
import com.stu.fitconnect.utils.ResourceJsonManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportClubInfoViewModel @Inject constructor(
    private val getSportClubInfoUseCase: GetSportClubInfoUseCase,
) : ViewModel(), SportClubInfoContract{

    private val mutableScreenState = MutableStateFlow(SportClubInfoContract.State())
    override val state: StateFlow<SportClubInfoContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SportClubInfoContract.Event) {
        when(event) {
            is SportClubInfoContract.Event.OnGetSportClub -> getSportClubInfo(event.id)
            else -> {}
        }
    }

    private fun getSportClubInfo(id: Int) = viewModelScope.launch {
        mutableScreenState.update { it.copy(isLoading = true) }
        try {
            //delay(2000)
            val sportClubInfo = getSportClubInfoUseCase.getSportClubById(id)
            mutableScreenState.update { it.copy(sportClub = sportClubInfo) }

            // todo get sport club info
        } catch (e: Exception) {
            // todo handle exception
            // add side effect?
        } finally {
            mutableScreenState.update { it.copy(isLoading = false) }
        }
    }

//    private fun getAllAmenities(availableAmenity: List<Int>) : List<AmenityWithAvailable> {
//        val jsonString = resourceManager.getRawJsonAsString(R.raw.sport_clubs_filters_data)
//        val allAmenity = Gson().fromJson(jsonString, Array<AmenityWithAvailable>::class.java).asList()
//        allAmenity.forEachIndexed { index, amenityWithAvailable ->
//            if(availableAmenity.contains(index))
//                amenityWithAvailable.available = true
//        }
//        return allAmenity
//    }
}