package com.example.cannadex.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cannadex.databinding.ActivityHomeBinding
import com.example.cannadex.ui.adapter.StrainAdapter
import com.example.cannadex.ui.viewmodel.HomeViewModel
import com.example.cannadex.utils.Status
import com.example.cannadex.utils.extensions.show

class HomeActivity : AppCompatActivity() {

    private val vm by viewModels<HomeViewModel>()
    private val binding by lazy { ActivityHomeBinding.inflate(LayoutInflater.from(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeObservables()

        binding.rvCannabis.apply {
            setHasFixedSize(true)
            adapter = StrainAdapter()
        }
    }

    private fun observeObservables() {
        vm.strainsObservable.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> binding.loader.show()
                Status.SUCCESS -> {
                }
                Status.ERROR -> {
                }
            }
        })
    }
}