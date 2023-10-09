package com.stu.fitconnect.base.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val ui: CoroutineDispatcher
    val io: CoroutineDispatcher
    val bg: CoroutineDispatcher
}