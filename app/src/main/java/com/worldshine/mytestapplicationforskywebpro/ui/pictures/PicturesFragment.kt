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
import com.worldshine.mytestapplicationforskywebpro.di.component.DaggerPresentersComponent
import com.worldshine.mytestapplicationforskywebpro.di.component.NetworkComponent
import com.worldshine.mytestapplicationforskywebpro.ui.authorization.AuthorizationFragmentPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class PicturesFragment : MvpAppCompatFragment(), PicturesFragmentView {

    private var _binding: FragmentPicturesBinding? = null
    private val binding get() = _binding!!
    private val adapter = PicturesAdapter()

    @Inject
    lateinit var presenterProvider: AuthorizationFragmentPresenter
    private val presenter: PicturesFragmentPresenter by moxyPresenter {
        DaggerPresentersComponent.create().getPicturesFragmentPresenter()
    }
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
        binding.btnRetry.setOnClickListener { adapter.retry() }
        presenter.getPictures().observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            binding.pbPictures.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

}