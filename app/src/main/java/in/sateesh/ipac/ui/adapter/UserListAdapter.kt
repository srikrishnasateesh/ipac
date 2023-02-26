package `in`.sateesh.ipac.ui.adapter

import `in`.sateesh.ipac.R
import `in`.sateesh.ipac.data.model.User
import `in`.sateesh.ipac.databinding.UserItem2Binding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.user_item.view.*

class UserListAdapter(val list: ArrayList<User>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: UserItem2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(user: User) {
            binding.user = user
            binding.url = user.picture?:""
            binding.container.setOnClickListener {
                if (adapterPosition > -1 && userListListener != null) {
                    userListListener?.onUserSelected(list[adapterPosition])
                }
            }
          /*  binding.apply {
                ivProfile.load(user.picture) {
                    placeholder(R.drawable.ic_launcher_background)
                }
                 tvUserName.text = user.name
                tvUserLocation.text = user.location
                if (user.gender != null) {
                    ivGender.visibility = View.VISIBLE
                    if (user.gender == "male") ivGender.load(R.drawable.ic_male) else ivGender.load(
                        R.drawable.ic_female)
                } else {
                    ivGender.visibility = View.GONE
                }
                tvAge.text = "${user.age} yrs"
                if (user.age > 0) tvAge.visibility = View.VISIBLE else tvAge.visibility = View.GONE

                root.setOnClickListener {
                    if (adapterPosition > -1 && userListListener != null) {
                        userListListener?.onUserSelected(adapterPosition)
                    }
                }
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        UserItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount() = list.size

    var userListListener: UserListListener? = null

    interface UserListListener {
        fun onUserSelected(user: User)
    }
}