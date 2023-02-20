package ir.maddev.payyourshare.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

/**
 * Use this class in order to call local db or api requests safely.
 */
@Singleton
class SafeSourceCalls {

    /**
     * Takes desired request as an input and launches the request in try-catch block.
     * @param dispatcher a thread in which the owner wants to call request on.
     * @param request lambda to safely call. It should be a suspend function because it's going to call a suspend function
     * from api or local data source.
     * @return [Response.Success] if the request completed successfully
     * @return [Response.Error] if the request faced an error
     */
    suspend fun <T> run(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        request: suspend CoroutineScope.() -> T
    ): Response<T> =
        withContext(dispatcher) {
            try {
                Response.Success(request())
            } catch (throwable: Throwable) {
                Response.Error(throwable, null)
            }
        }
}