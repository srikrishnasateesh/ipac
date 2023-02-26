package `in`.sateesh.ipac.data.remote.api

import `in`.sateesh.ipac.util.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

object SafeApiRequest {
    open suspend fun <T> safeApiCall( apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                var code = -1
                if (throwable is HttpException) {
                    code = throwable.code()
                }
                ResultWrapper.Error(code, throwable.message?:"")
            }
        }
    }
}