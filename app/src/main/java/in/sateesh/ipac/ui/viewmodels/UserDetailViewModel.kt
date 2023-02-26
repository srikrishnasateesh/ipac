package `in`.sateesh.ipac.ui.viewmodels

import `in`.sateesh.ipac.data.model.User
import `in`.sateesh.ipac.util.DataFormatter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDetailViewModel : ViewModel(){

    var userName: String = ""
    var location: String = ""
    var email: String = ""
    var phone: String = ""
    var cell:String = ""
    var dob: String = ""
    var age: String = ""
    var gender:String = ""
    var registerOn:String = ""
   // var url:String = ""



    fun init(user:User){
        //url = user.picture?:""
        userName = user.name?:"--"
        location = user.location?:"--"
        dob = DataFormatter.parseDate(user.dobDate)?:"--"
        email = user.email?:"--"
        phone = user.phone?:"--"
        cell=user.cell?:"--"
        age=user.age.toString()?:"--"
        gender=(user.gender?:"--").uppercase()
        registerOn=user.registerAge.toString()?:"--"
    }
}