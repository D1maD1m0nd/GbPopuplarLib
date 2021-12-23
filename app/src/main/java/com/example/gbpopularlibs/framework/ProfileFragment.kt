package com.example.gbpopularlibs.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gbpopularlibs.data.model.GitHubUser
import com.example.gbpopularlibs.data.model.User
import com.example.gbpopularlibs.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private  var user: User? = null
    private var userGitHub: GitHubUser? = null
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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
        user?.let {
            binding.userTextView.text = it.login
        }

        userGitHub?.let {
            binding.userTextView.text = it.login
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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