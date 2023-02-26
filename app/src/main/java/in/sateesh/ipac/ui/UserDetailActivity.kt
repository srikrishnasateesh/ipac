package `in`.sateesh.ipac.ui

import `in`.sateesh.ipac.R
import `in`.sateesh.ipac.data.model.User
import `in`.sateesh.ipac.databinding.ActivityUserDetailsBinding
import `in`.sateesh.ipac.ui.viewmodels.UserDetailViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailActivity : AppCompatActivity() {

    val viewModel: UserDetailViewModel by viewModels()

    lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        init()
    }

    private fun init() {
        intent?.extras?.let {
            if (it.containsKey("user")) {

                val user = it.getParcelable<User>("user")
                user?.let { it1 ->
                    viewModel.init(it1)
                    binding.viewModel = viewModel
                    binding.url = it1.picture?:""
                }
            }
        }
        binding.tvBack.setOnClickListener {
            finish()
        }
    }
}