package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.worldshine.mytestapplicationforskywebpro.adapters.LoadStateAdapter
import com.worldshine.mytestapplicationforskywebpro.adapters.PicturesAdapter
import com.worldshine.mytestapplicationforskywebpro.databinding.FragmentPicturesBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PicturesFragment : MvpAppCompatFragment(), PicturesFragmentView {

    private var _binding: FragmentPicturesBinding? = null
    private val binding get() = _binding!!
    private val adapter = PicturesAdapter()
    private val presenter by moxyPresenter { PicturesFragmentPresenter() }
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPicturesBinding.inflate(inflater, container, false)
        recyclerView = binding.rvPictures
        recyclerView.adapter = adapter
        initAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun initAdapter() {
        binding.retryButton.setOnClickListener { adapter.retry() }
        presenter.getPictures().observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

}