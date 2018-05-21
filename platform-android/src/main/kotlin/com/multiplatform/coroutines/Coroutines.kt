package com.multiplatform.coroutines

import kotlinx.coroutines.experimental.async
import kotlin.coroutines.experimental.CoroutineContext

actual fun <T> async(context: CoroutineContext, block: suspend () -> T): Deferred<T> {
    return Deferred(async {
        kotlinx.coroutines.experimental.withContext(context, block = block)
    })
}

actual fun <T> launch(context: CoroutineContext, block: suspend () -> T) {
    kotlinx.coroutines.experimental.launch(context) {
        block()
    }
}

actual suspend fun <T> withContext(context: CoroutineContext, block: suspend () -> T): T {
    return kotlinx.coroutines.experimental.withContext(context = context, block = block)
}

