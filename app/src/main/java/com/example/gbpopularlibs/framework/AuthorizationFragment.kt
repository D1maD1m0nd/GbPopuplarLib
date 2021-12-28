package com.example.gbpopularlibs.framework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.gbpopularlibs.R
import com.example.gbpopularlibs.app.App.Companion.app
import com.example.gbpopularlibs.databinding.FragmentAuthorizationBinding
import com.example.gbpopularlibs.mvp.AuthContract
import com.example.gbpopularlibs.mvp.AuthPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class AuthorizationFragment : MvpAppCompatFragment(), AuthContract.View {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!
    private val presenter by moxyPresenter {
        AuthPresenter().apply {
            app.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        loginTextInputLayout.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not()) {
                val login = loginTextInputLayout.text.toString()
                presenter.onChangeLogin(login)
            }
        }

        passwordTextInputLayout.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus.not()) {
                val password = passwordTextInputLayout.text.toString()
                presenter.onChangePassword(password)
            }
        }

        authButton.setOnClickListener {
            val login = loginTextInputLayout.text.toString()
            val password = passwordTextInputLayout.text.toString()
            presenter.onAuthButtonClick(login, password)
        }
    }

    override fun setAuthState(state: AuthContract.AuthState) {
        when (state) {
            AuthContract.AuthState.ERROR -> errorAuthValidate()
            AuthContract.AuthState.SUCCESS -> successAuthValidate()
        }
    }


    private fun errorAuthValidate() {
        showErrorDialog()
    }

    private fun showErrorDialog() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(getString(R.string.do_not_auth))
            ?.setTitle(getString(R.string.Error))
            ?.setNegativeButton(
                R.string.ok
            ) { dialog, _ ->
                dialog.dismiss()
            }
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    private fun successAuthValidate() {
        showSuccessDialog()
    }

    private fun showSuccessDialog() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(getString(R.string.success_auth))
            ?.setTitle(getString(R.string.Auth))
            ?.setPositiveButton(
                R.string.ok
            ) { dialog, _ ->
                dialog.dismiss()

            }
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    private fun loadingAuthValidate() {

    }

    override fun setPasswordState(state: AuthContract.PasswordState) = with(binding) {
        when (state) {
            AuthContract.PasswordState.SUCCESS -> {

            }
            AuthContract.PasswordState.INCORRECT_PASSWORD -> {
                passwordTextInputLayout.error = getString(R.string.InсorectPasswordError)
            }
        }
    }

    override fun setLoginState(state: AuthContract.LoginState) = with(binding) {
        when (state) {
            AuthContract.LoginState.SUCCESS -> {}
            AuthContract.LoginState.INCORRECT_LOGIN -> {
                loginTextInputLayout.error = getString(R.string.LoginIncorrectError)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = AuthorizationFragment()
    }
}