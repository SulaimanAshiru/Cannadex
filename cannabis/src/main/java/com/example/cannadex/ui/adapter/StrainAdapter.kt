package com.example.cannadex.ui.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cannadex.data.model.Strain
import com.example.cannadex.databinding.ItemCannabisBinding
import com.example.cannadex.databinding.LoaderItemScrollBinding
import com.example.cannadex.utils.ViewType
import com.example.cannadex.utils.extensions.listen
import com.example.cannadex.utils.extensions.loadUrl

class StrainAdapter(
    private val listener: (Strain) -> Unit
) : RecyclerView.Adapter<StrainAdapter.StrainViewHolder>() {

    private val strains: MutableList<Strain> = mutableListOf()
    private val loader by lazy { Strain().apply { viewType = ViewType.LOADER } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ViewType.LOADER.ordinal -> {
            LoaderItemScrollBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).let { StrainViewHolder(it) }
        }
        else -> {
            ItemCannabisBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
                .let { StrainViewHolder(it).listen { position -> listener.invoke(strains[position]) } }
        }
    }


    override fun getItemCount() = strains.size

    override fun onBindViewHolder(holder: StrainViewHolder, position: Int) {
        holder.loadStrain(strains[position])
    }

    override fun getItemViewType(position: Int) = when (strains[position].viewType) {
        ViewType.LOADER -> ViewType.LOADER.ordinal
        ViewType.ITEM -> ViewType.ITEM.ordinal
    }

    fun loadStrains(strains: List<Strain>) {
        this.strains.apply {
            addAll(strains)
            notifyDataSetChanged()
        }
    }

    fun addLoader() {
        if (strains.isNotEmpty())
            Handler(Looper.getMainLooper()).post {
                strains.add(loader)
                notifyItemInserted(strains.size - 1)
            }
    }

    fun removeLoader() {
        if (strains.contains(loader)) {
            strains.remove(loader)
            notifyItemRemoved(strains.size)
        }
    }

    class StrainViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun loadStrain(strain: Strain) {
            when (binding) {
                is ItemCannabisBinding -> binding.apply {
                    ivCannabis.loadUrl(strain.image)
                    tvCannabisName.text = strain.name?.trim()
                }
                is LoaderItemScrollBinding -> {
                }
            }

        }
    }
}
