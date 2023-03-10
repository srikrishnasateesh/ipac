package `in`.sateesh.ipac.util

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(val code: Int? = null, val error: String?): ResultWrapper<Nothing>()
}
