package com.example.cannadex.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cannadex.databinding.FragmentStrainDetailBinding

class StrainDetailFragment : Fragment() {

    private lateinit var binding: FragmentStrainDetailBinding
    private val args: StrainDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentStrainDetailBinding.inflate(
        LayoutInflater.from(context),
        container,
        false
    ).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvName.text = args.strain.name
        }
    }
}