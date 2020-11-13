package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.worldshine.mytestapplicationforskywebpro.R

class AuthorizationFragment : Fragment() {

    private lateinit var dashboardViewModel: AuthorizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(AuthorizationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_authorization, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}