package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.worldshine.mytestapplicationforskywebpro.R
import com.worldshine.mytestapplicationforskywebpro.databinding.FragmentAuthorizationBinding
import com.worldshine.mytestapplicationforskywebpro.di.component.DaggerPresentersComponent
import com.worldshine.mytestapplicationforskywebpro.utils.createSnackbar
import com.worldshine.mytestapplicationforskywebpro.utils.isValidEmail
import com.worldshine.mytestapplicationforskywebpro.utils.isValidPassword
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.util.*
import javax.inject.Inject

class AuthorizationFragment : MvpAppCompatFragment(), AuthorizationFragmentView {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: AuthorizationFragmentPresenter
    private val presenter: AuthorizationFragmentPresenter by moxyPresenter {
        DaggerPresentersComponent.create().getAuthorizationFragmentPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        setOnTouchListener()
        btnOnClick()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun btnOnClick() {
        binding.btnDo.setOnClickListener {
            val email =
                binding.tfEmail.text?.trim().toString()
                    .toLowerCase(Locale.getDefault())
            val password = binding.tfPassword.text?.trim().toString()
            binding.tilEmail.error = null
            binding.tilPassword.error = null
            if (email.isEmpty() || password.isEmpty() || !email.isValidEmail() || !password.isValidPassword()) {
                if (email.isEmpty()) {
                    binding.tilEmail.error = getString(R.string.email_empty)
                } else if (!email.isValidEmail()) {
                    binding.tilEmail.error = getString(R.string.email_error)
                }
                if (password.isEmpty()) {
                    binding.tilPassword.error =
                        getString(R.string.password_empty)
                } else if (!password.isValidPassword()) {
                    binding.tilPassword.error =
                        getString(R.string.password_error)
                }
            } else if (email.isNotEmpty() && password.isNotEmpty() && email.isValidEmail() && password.isValidPassword()) {
                presenter.getWeather()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListener() {
        binding.root.setOnTouchListener { view, _ ->
            view.performClick()
            hideKeyboard()
            return@setOnTouchListener true
        }
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            binding.root.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    override fun showSnackbars(
        city: String,
        weather: String,
        clouds: String,
        humidity: String
    ) {
        createSnackbar(
            binding.root,
            String.format(
                getString(R.string.weatherShow),
                city,
                weather,
                clouds,
                humidity
            )
        )
    }

    override fun showError(error: String) {
        createSnackbar(
            binding.root,
            error
        )
    }

    override fun showProgressbar(show: Boolean) {
        binding.pbAuthorization.isVisible = show
    }

}