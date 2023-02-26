package `in`.sateesh.ipac.ui.viewmodels

import `in`.sateesh.ipac.data.model.User
import `in`.sateesh.ipac.data.model.UserResponse
import `in`.sateesh.ipac.data.repository.UserRepository
import `in`.sateesh.ipac.util.Coroutines
import `in`.sateesh.ipac.util.ResultWrapper
import `in`.sateesh.ipac.util.connectivity.ConnectivityObserver
import `in`.sateesh.ipac.util.connectivity.NetworkConnectivityObserver
import `in`.sateesh.ipac.util.userResultToUser
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UserListViewModel"
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val connectivityObserver: NetworkConnectivityObserver
) : ViewModel() {

    var _showLoader = MutableLiveData<Boolean>()
    var showLoader: LiveData<Boolean> = _showLoader

    val _errorResponse = MutableLiveData<String>()
    var errorResponse: LiveData<String> = _errorResponse

    private var _list = MutableLiveData<ArrayList<User>>()
    var list: LiveData<ArrayList<User>> = _list


    var page = 1
    fun init() {

        Coroutines.io {
            if(!connectivityObserver.isNetworkAvailable()){
                fetchSavedUsers()
            }
            connectivityObserver.observe().collect{
                Log.d(TAG, "init1: $it")
                if(it == ConnectivityObserver.Status.Available){
                    page =1
                    usersList()
                }else{
                    fetchSavedUsers()
                }
            }
        }


    }

    private suspend fun fetchSavedUsers(){
        val users = userRepository.usersList()
        _list.postValue(users as ArrayList<User>?)
    }

    private fun usersList() {
        Coroutines.io {
            when (val result = userRepository.usersList(page = page)) {
                is ResultWrapper.Success -> {
                    handleList(result.value.body())
                }
                is ResultWrapper.Error -> {
                    _errorResponse.postValue(result.error ?: "Something went wrong")
                }
            }
        }
    }

    private fun handleList(userResponse: UserResponse?) {
        userResponse?.let {
           it.results?.let {userList->
               var users: ArrayList<User> = userList.map {userResult->
                   userResultToUser(userResult)
               }.toList() as ArrayList<User>

               _list.postValue(users)
               Coroutines.io {
                   userRepository.insertUsers(users)
               }
           }
        }
    }

    fun moreList() {
        page += 1
        usersList()
    }
}