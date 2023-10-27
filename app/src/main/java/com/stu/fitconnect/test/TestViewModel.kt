package com.stu.fitconnect.test

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(): ViewModel() /*TestContract*/ {

/*    private val mutableScreenState = MutableStateFlow(TestContract.State())
    override val state: StateFlow<TestContract.State> = mutableScreenState.asStateFlow()
    override fun event(event: TestContract.Event) {
        val i = 0
    }*/

}