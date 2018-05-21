package com.multiplatform.coroutines

import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

actual class Deferred<out T>(
        private val context: CoroutineContext,
        private val block: suspend () -> T
) {

    actual suspend fun await(): T {
        return suspendCoroutineOrReturn { continuation ->
            block.startCoroutine(WrappedContinuation(context, continuation))
            COROUTINE_SUSPENDED
        }
    }

}