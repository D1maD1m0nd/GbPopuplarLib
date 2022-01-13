package com.example.gbpopularlibs.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbpopularlibs.app.App
import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.databinding.FragmentUsersBinding
import com.example.gbpopularlibs.mvp.user.UsersContract
import com.example.gbpopularlibs.mvp.user.UsersContract.State
import com.example.gbpopularlibs.mvp.user.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment() : MvpAppCompatFragment(), UsersContract.View{
    private var _binding : FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val itemClickListener = object : ItemClickListener {
        override fun itemClick(pos: Int) {
            onItemClick(pos)
        }
    }

    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            App.app.appComponent.inject(this)
        }
    }

    private val adapter = UserAdapter(itemClickListener)
    private var list = mutableListOf<GitHubUser>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        presenter.getUsers()
    }
    private fun initView() = with(binding){
        userContainerRcView.layoutManager = LinearLayoutManager(context)
        userContainerRcView.adapter = adapter
    }
    override fun onItemClick(pos: Int) {
        val login = list[pos].login
        presenter.showUser(login!!)

    }

    override fun showUsersList(users: List<GitHubUser>) {
        list = users as MutableList<GitHubUser>
        adapter.setData(users)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    override fun setState(state: State) {
        when(state) {
            State.IDLE -> {
                binding.progressBar.visibility = View.GONE
            }
            State.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

}