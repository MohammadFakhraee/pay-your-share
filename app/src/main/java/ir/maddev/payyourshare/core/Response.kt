package ir.maddev.payyourshare.core

sealed interface Response<out T> {
    data class Success<out T>(val data: T?) : Response<T>
    data class Error<out T>(val error: Throwable, val data: T?) : Response<T>
    data class Loading<out T>(val data: T?) : Response<T>
}
