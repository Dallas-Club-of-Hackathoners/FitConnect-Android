package com.stu.fitconnect.features.sportclubs.presentation.selectedclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.fitconnect.features.sportclubs.domain.usecases.GetSportClubInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportClubInfoViewModel @Inject constructor(
    private val getSportClubInfoUseCase: GetSportClubInfoUseCase
) : ViewModel(), SportClubInfoContract{

    private val mutableScreenState = MutableStateFlow(SportClubInfoContract.State())
    override val state: StateFlow<SportClubInfoContract.State> = mutableScreenState.asStateFlow()


    override fun event(event: SportClubInfoContract.Event) = when(event) {
        is SportClubInfoContract.Event.OnGetSportClub -> getSportClubInfo(event.id)
    }

    private fun getSportClubInfo(id:Int) {
        mutableScreenState.update { it.copy(isLoading = true) }
        try {
            viewModelScope.launch {
                val sportClubInfo = getSportClubInfoUseCase.getSportClubById(
                    mutableScreenState.value.id
                )
                mutableScreenState.update { it.copy( sportClub = sportClubInfo) }
            }

            // todo get sport club info
        } catch (e: Exception) {
            // todo handle exception
            // add side effect?
        } finally {
            mutableScreenState.update { it.copy(isLoading = false) }
        }
    }
}