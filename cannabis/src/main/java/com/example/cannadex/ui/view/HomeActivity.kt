package com.example.cannadex.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cannadex.databinding.ActivityHomeBinding
import com.example.cannadex.ui.adapter.StrainAdapter
import com.example.cannadex.ui.viewmodel.HomeViewModel
import com.example.cannadex.utils.Status
import com.example.cannadex.utils.extensions.show
import com.example.cannadex.utils.extensions.toast

// TODO: 8/11/2020 Move details to fragment
class HomeActivity : AppCompatActivity() {

    private val vm by viewModels<HomeViewModel>()
    private val binding by lazy { ActivityHomeBinding.inflate(LayoutInflater.from(this)) }
    private val adapter by lazy {
        StrainAdapter() {
            toast(it.name)
            // TODO: 8/11/2020 Navigate to details page
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeObservables()

        binding.rvCannabis.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@HomeActivity.adapter
        }
    }

    private fun observeObservables() {
        vm.strainsObservable.observe(this, Observer { resource ->
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