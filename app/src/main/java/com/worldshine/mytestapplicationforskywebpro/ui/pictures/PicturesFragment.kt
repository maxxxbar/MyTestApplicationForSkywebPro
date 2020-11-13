package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.worldshine.mytestapplicationforskywebpro.R

class PicturesFragment : Fragment() {

    private lateinit var homeViewModel: PicturesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(PicturesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pictures, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}