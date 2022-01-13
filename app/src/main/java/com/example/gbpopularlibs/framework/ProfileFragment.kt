package com.example.gbpopularlibs.framework

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gbpopularlibs.app.App
import com.example.gbpopularlibs.data.model.GitHubRepo
import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.data.model.User
import com.example.gbpopularlibs.databinding.FragmentProfileBinding
import com.example.gbpopularlibs.mvp.profile.ProfileContract
import com.example.gbpopularlibs.mvp.profile.ProfilePresenter
import com.example.gbpopularlibs.mvp.user.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.MvpFragment
import moxy.ktx.moxyPresenter
import android.widget.ArrayAdapter




class ProfileFragment : MvpAppCompatFragment(), ProfileContract.View {
    private  var user: User? = null
    private var userGitHub: GitHubUser? = null
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // используем адаптер данных
    var adapter: ArrayAdapter<String>? = null
    private val presenter by moxyPresenter {
        ProfilePresenter().apply {
            App.app.appComponent.provideProfileComponent().build().inject(this)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{ bundle ->
            bundle.getParcelable<User>(ARG_USER)?.let {
                user = it
            }
            bundle.getParcelable<GitHubUser>(ARG_GIT_HUB_USER)?.let {
                userGitHub = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    fun initView() {
        user?.let {
            binding.userTextView.text = it.login
        }

        userGitHub?.let {
            binding.userTextView.text = it.login
        }
        val login =  binding.userTextView.text.toString()
        if(login.isNotEmpty()) {
            presenter.initReposList(binding.userTextView.text.toString())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setState(state: ProfileContract.AuthState) {
        when(state) {
            ProfileContract.AuthState.ERROR -> binding.progressBar.hide()
            ProfileContract.AuthState.SUCCESS ->{
                binding.progressBar.hide()
            }
            ProfileContract.AuthState.LOADING -> binding.progressBar.show()
        }
    }

    override fun setRepoList(list: List<GitHubRepo>) {
        val adapterList = list.map {
            it.name
        }
        adapter = ArrayAdapter<String>(
            binding.root.context, R.layout.simple_list_item_1, adapterList
        )
        binding.repoListListView.adapter = adapter
    }

    companion object {
        private const val ARG_USER = "ARG_USER"
        private const val ARG_GIT_HUB_USER = "ARG_GIT_HUB_USER"
        fun newInstance(user: User) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, user)
                }
            }
        fun newInstance(user: GitHubUser) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_GIT_HUB_USER, user)
                }
            }
    }
}