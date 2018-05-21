package com.multiplatform.coroutines

import kotlinx.coroutines.experimental.Deferred

actual class Deferred<out T>(
    private val wrappedDeferred: Deferred<T>
) {

    actual suspend fun await(): T {
        return wrappedDeferred.await()
    }

}