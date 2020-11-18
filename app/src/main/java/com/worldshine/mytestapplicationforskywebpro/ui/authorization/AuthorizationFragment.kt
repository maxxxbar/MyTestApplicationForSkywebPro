package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.worldshine.mytestapplicationforskywebpro.R
import com.worldshine.mytestapplicationforskywebpro.databinding.FragmentAuthorizationBinding
import com.worldshine.mytestapplicationforskywebpro.di.component.DaggerPresentersComponent
import com.worldshine.mytestapplicationforskywebpro.utils.createSnackbar
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
        setTouchListener()
        btnOnClick()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun btnOnClick() {
        binding.btnDo.setOnClickListener {
            val email =
                binding.tfEmail.text?.trim().toString()
                    .toLowerCase(Locale.getDefault())
            val password = binding.tfPassword.text?.trim().toString()
            presenter.checkEmailAndPasswordOnValid(
                email = email,
                password = password
            )
        }
        binding.tilPassword.setEndIconOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.password_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun showErrorInTextInputLayoutEmail(error: Int) {
        binding.tilEmail.error = getString(error)
    }

    override fun showErrorInTextInputLayoutPassword(error: Int) {
        binding.tilPassword.error = getString(error)
    }

    override fun clearErrorForEmailAndPasswordTextField() {
        binding.tilEmail.error = null
        binding.tilPassword.error = null
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener() {
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