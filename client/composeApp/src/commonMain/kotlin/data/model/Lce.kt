package data.model

sealed class Lce<out T> {
    data object Loading : Lce<Nothing>()

    data class Content<T>(val data: T) : Lce<T>()

    data class Error(val throwable: Throwable) : Lce<Nothing>()

    val isLoading: Boolean
        get() = this is Loading

    fun getIfContent(): T? {
        return (this as? Content)?.data
    }

    fun getError(): Throwable? {
        return (this as? Error)?.throwable
    }
}
