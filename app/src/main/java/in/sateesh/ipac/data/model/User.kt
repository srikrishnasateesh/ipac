package `in`.sateesh.ipac.data.model

import `in`.sateesh.ipac.R
import `in`.sateesh.ipac.constants.AppConstants
import android.graphics.Color
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import coil.load
import kotlinx.android.parcel.Parcelize

@Entity(tableName = AppConstants.USERS_TABLE)
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId:Long?=null,
    var name:String,
    var gender:String?=null,
    var email:String?=null,
    var location:String?=null,
    var username:String?=null,
    var dobDate:String?=null,
    var age:Int,
    var registerAge:Int,
    var phone:String?=null,
    var cell:String?=null,
    var picture:String?=null,
):Parcelable{

    companion object{
        @JvmStatic
        @BindingAdapter("profile")
        fun loadImage(view:ImageView,url:String){
            view.load(url){
                placeholder(R.drawable.ic_launcher_background)
            }
        }

        @JvmStatic
        @BindingAdapter("gender")
        fun genderImage(view:ImageView,type:String){
            if(type=="male") view.setImageResource(R.drawable.ic_male) else view.setImageResource(R.drawable.ic_female)
        }

        @JvmStatic
        @BindingAdapter("bgcolor")
        fun bgColor(view: LinearLayout, type:String){
            if(type.lowercase()=="male") view.setBackgroundColor(Color.parseColor("#FF6200EE")) else view.setBackgroundColor(Color.parseColor("#FF018786"))
        }
    }
}
