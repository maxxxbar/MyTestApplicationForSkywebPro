package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.worldshine.mytestapplicationforskywebpro.adapters.PicturesAdapter
import com.worldshine.mytestapplicationforskywebpro.data.PicturesRepository
import com.worldshine.mytestapplicationforskywebpro.databinding.FragmentPicturesBinding
import com.worldshine.mytestapplicationforskywebpro.network.Connection
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PicturesFragment : Fragment() {

    private var _binding: FragmentPicturesBinding? = null
    private val binding get() = _binding!!
    private val adapter = PicturesAdapter()
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPicturesBinding.inflate(inflater, container, false)

        val recyclerView = binding.rvPictures
        recyclerView.adapter = adapter

        val qwe = PicturesRepository(Connection.create)
        qwe.getResultAsLiveData().observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
        return binding.root
    }

    private fun getPictures() {
        job?.cancel()
        job = lifecycleScope.launch {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}