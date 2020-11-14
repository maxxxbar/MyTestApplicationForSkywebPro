package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.worldshine.mytestapplicationforskywebpro.R
import com.worldshine.mytestapplicationforskywebpro.databinding.FragmentAuthorizationBinding
import com.worldshine.mytestapplicationforskywebpro.network.Connection
import com.worldshine.mytestapplicationforskywebpro.utils.isValidEmail
import com.worldshine.mytestapplicationforskywebpro.utils.isValidPassword
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import java.util.*

class AuthorizationFragment : MvpAppCompatFragment() {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

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
        binding.buttonDo.setOnClickListener {
            val email =
                binding.activityMainTextfieldEmail.text?.trim().toString()
                    .toLowerCase(Locale.getDefault())
            val password = binding.activityMainTextfieldPassword.text?.trim().toString()
            binding.activityMainTextInputLayoutEmail.error = null
            binding.activityMainTextInputLayoutPassword.error = null
            if (email.isEmpty() || password.isEmpty() || !email.isValidEmail() || !password.isValidPassword()) {
                if (email.isEmpty()) {
                    binding.activityMainTextInputLayoutEmail.error = getString(R.string.email_empty)
                } else if (!email.isValidEmail()) {
                    binding.activityMainTextInputLayoutEmail.error = getString(R.string.email_error)
                }
                if (password.isEmpty()) {
                    binding.activityMainTextInputLayoutPassword.error =
                        getString(R.string.password_empty)
                } else if (!password.isValidPassword()) {
                    binding.activityMainTextInputLayoutPassword.error =
                        getString(R.string.password_error)
                }
            } else if (email.isNotEmpty() && password.isNotEmpty() && email.isValidEmail() && password.isValidPassword()) {


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
}