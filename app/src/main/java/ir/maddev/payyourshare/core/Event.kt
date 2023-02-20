package ir.maddev.payyourshare.core

data class Event<out T>(
    private val content: T, private var isHandled: Boolean = false
) {
    fun getContentOrNull(): T? {
        if (isHandled) return null
        isHandled = true
        return content
    }

    fun peekContent(): T = content
}