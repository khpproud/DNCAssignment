package dev.patrick.dncassignment.base

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

//object AppDispatchers @VisibleForTesting @Inject constructor(
//    val main: CoroutineDispatcher = Dispatchers.Main,
//    val io: CoroutineDispatcher = Dispatchers.IO,
//    val computation: CoroutineDispatcher = Dispatchers.Default
//)

@Singleton
class AppDispatchers @VisibleForTesting @Inject constructor() {
    @VisibleForTesting
    var main: CoroutineDispatcher = Dispatchers.Main
        private set

    @VisibleForTesting
    var io: CoroutineDispatcher = Dispatchers.IO
        private set

    @VisibleForTesting
    var computation = Dispatchers.Default
        private set
}