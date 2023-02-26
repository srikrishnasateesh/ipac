package `in`.sateesh.ipac.data.repository

import `in`.sateesh.ipac.data.db.dao.UserDao
import `in`.sateesh.ipac.data.model.User
import `in`.sateesh.ipac.data.remote.api.SafeApiRequest
import `in`.sateesh.ipac.data.remote.api.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api:UserApi,
    val userDao: UserDao
) {
    suspend fun usersList (page:Int) = SafeApiRequest.safeApiCall {
        api.usersList(page = page)
    }

    suspend fun insertUsers(users:List<User>) = userDao.insertUsers(users)

    suspend fun usersList() = userDao.list()
}