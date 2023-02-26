package `in`.sateesh.ipac.ui

import `in`.sateesh.ipac.R
import `in`.sateesh.ipac.data.model.User
import `in`.sateesh.ipac.databinding.ActivityUsersBinding
import `in`.sateesh.ipac.ui.adapter.UserListAdapter
import `in`.sateesh.ipac.ui.viewmodels.UserListViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_users.*

@AndroidEntryPoint
class UsersListActivity:AppCompatActivity(), UserListAdapter.UserListListener {

    private val userListViewModel:UserListViewModel by viewModels()
    var usersList:ArrayList<User> = ArrayList()
    lateinit var userListAdapter: UserListAdapter

    lateinit var binding:ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        handleObservers()
    }

    private fun initialize(){
        binding.toolbar.title = "Users"
        setSupportActionBar(binding.toolbar)


        userListAdapter = UserListAdapter(usersList)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@UsersListActivity,2)
            adapter = userListAdapter
            userListAdapter.notifyDataSetChanged()
        }
        userListAdapter.userListListener = this@UsersListActivity
        setRecycleViewScroll()
        userListViewModel.init()
    }

    private fun handleObservers(){
        userListViewModel?.apply {
            list.observe(this@UsersListActivity){
               updateList(it)
            }
        }
    }

    private fun updateList(it: ArrayList<User>) {
        if(userListViewModel.page==1){
            usersList.clear()
        }
        usersList.addAll(it)
        userListAdapter.notifyDataSetChanged()
        binding.toolbar.title = "Users(${usersList.size})"
    }

    private fun setRecycleViewScroll() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        userListViewModel.moreList()
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                        return
                    }
                }
            }
        })
    }

    override fun onUserSelected(user: User) {
        var bundle = Bundle()
        bundle.putParcelable("user",user)
        Intent(this@UsersListActivity,UserDetailActivity::class.java).also {
            it.putExtras(bundle)
            startActivity(it)
        }
    }


}