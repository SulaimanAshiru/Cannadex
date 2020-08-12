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
import com.example.cannadex.utils.Status
import com.example.cannadex.utils.extensions.show
import com.example.cannadex.utils.extensions.toast

class StrainsFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentStrainsBinding.inflate(
        LayoutInflater.from(context),
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeObservables()

        binding.rvCannabis.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@StrainsFragment.adapter
        }
    }

    private fun observeObservables() {
        vm.strainsObservable.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> binding.loader.root.show()
                Status.SUCCESS -> {
                    resource.data?.strains?.let { list -> adapter.loadStrains(list) }
                    binding.loader.root.show(false)
                }
                Status.ERROR -> {
                    // TODO: 8/11/2020 Show Error Dialog
                }
            }
        })
    }
}