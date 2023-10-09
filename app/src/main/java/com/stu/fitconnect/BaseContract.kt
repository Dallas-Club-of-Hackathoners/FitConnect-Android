package com.stu.fitconnect

interface BaseContract : BaseUnidirectionalViewModel<BaseContract.BaseState, BaseContract.BaseEvent, BaseContract.BaseEffect> {

    sealed class BaseState {
        object OnLoading : BaseState()
        object OnLoadingDialog : BaseState()
        data class OnError(val errorMessage: String) : BaseState()
        object OnSuccess : BaseState()
    }

    sealed class BaseEffect {
        object OnBackPressed : BaseEffect()
    }

    sealed class BaseEvent {
        object OnBackPressed : BaseEvent()
        object OnRetryPressed : BaseEvent()
    }
}