package com.example.cannadex.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cannadex.databinding.FragmentStrainsBinding
import com.example.cannadex.ui.adapter.StrainAdapter
import com.example.cannadex.ui.viewmodel.HomeViewModel
import com.example.cannadex.utils.OnLoadMoreListener
import com.example.cannadex.utils.RecyclerViewLoadMoreScroll
import com.example.cannadex.utils.Status
import com.example.cannadex.utils.ViewType
import com.example.cannadex.utils.extensions.show
import com.example.cannadex.utils.extensions.toast

class StrainsFragment : Fragment(), OnLoadMoreListener {

    private val vm by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentStrainsBinding
    private val adapter by lazy {
        StrainAdapter() { strain ->
            context?.toast(strain.name)
            StrainsFragmentDirections.goToStrainDetail(strain).let {
                findNavController().navigate(it)
            }
        }
    }
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentStrainsBinding.inflate(
        LayoutInflater.from(context),
        container,
        false
    ).also { binding = it }.root


/*

 Add Icon
 SplashScreen
 HomePage that shows me 3 horizontal rv
 Detail page for strains and whatever else
 bonus: Dark Mode
 */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeObservables()

        binding.rvCannabis.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapter?.getItemViewType(position)) {
                            ViewType.LOADER.ordinal -> 2
                            else -> 1
                        }
                    }
                }
            }
            adapter = this@StrainsFragment.adapter
            RecyclerViewLoadMoreScroll(layoutManager as GridLayoutManager)
                .also { listener ->
                    addOnScrollListener(listener)
                    scrollListener = listener
                }
                .apply { setOnLoadMoreListener(this@StrainsFragment) }
        }
    }

    private fun observeObservables() {
        vm.strainsObservable.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> toggleLoading()
                Status.SUCCESS -> {
                    resource.data?.strains?.let { list ->
                        adapter.loadStrains(list)
                    }
                    toggleLoading(false)
                }
                Status.ERROR -> {
                    // TODO: 8/11/2020 Show Error Dialog
                }
            }
        })
    }

    private fun toggleLoading(isLoading: Boolean = true) {
        if (vm.strainsAlreadyLoaded) {
            scrollListener.setLoaded()
            binding.loader.root.show(false)
            if (isLoading) adapter.addLoader() else adapter.removeLoader()
        } else {
            if (!isLoading) scrollListener.setLoaded()
            binding.loader.root.show(isLoading)
        }
    }

    override fun onLoadMore() {
        // get more data
        vm.loadMoreUsingPageNumber()
        context?.toast("Bottom")
    }
}