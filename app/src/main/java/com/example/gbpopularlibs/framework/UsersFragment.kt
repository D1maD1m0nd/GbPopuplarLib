package com.example.gbpopularlibs.framework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gbpopularlibs.R
import com.example.gbpopularlibs.app.App
import com.example.gbpopularlibs.databinding.FragmentUsersBinding
import com.example.gbpopularlibs.mvp.AuthPresenter
import com.example.gbpopularlibs.mvp.UsersContract
import com.example.gbpopularlibs.mvp.UsersContract.*
import com.example.gbpopularlibs.mvp.UsersPresenter
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
        UsersPresenter(App.app.router)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(list.isEmpty()) {
            initList()
        }
    }
    private val adapter = UserAdapter(itemClickListener)
    private val list = mutableListOf<String>()
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
    }
    private fun initList() {
        val users = resources.getStringArray(R.array.users)
        list.addAll(users)
    }
    private fun initView() = with(binding){
        adapter.setData(list)
        userContainerRcView.layoutManager = LinearLayoutManager(context)
        userContainerRcView.setHasFixedSize(true)
        userContainerRcView.adapter = adapter
    }
    override fun onItemClick(pos: Int) {
        val login = list[pos]
        if(login.isNotEmpty()) {
            presenter.showUser(login)
        }
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