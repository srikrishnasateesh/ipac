package `in`.sateesh.ipac.data.remote.api

import `in`.sateesh.ipac.data.model.UserResponse
import `in`.sateesh.ipac.data.model.UserResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api")
    suspend fun usersList(
        @Query("results") limit:Int = 20,
        @Query("page") page:Int
    ):Response<UserResponse?>
}