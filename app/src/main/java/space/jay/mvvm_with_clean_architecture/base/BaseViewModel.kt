package space.jay.mvvm_with_clean_architecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + CoroutineExceptionHandler { coroutineContext, throwable ->
            println("${throwable.message} = ${coroutineContext.job}")
        }

    fun launchWithIO(
        context : CoroutineDispatcher = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = launch(
        context = context,
        start = start,
        block = block
    )
}