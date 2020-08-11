package com.example.cannadex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cannadex.data.model.Strain
import com.example.cannadex.databinding.ItemCannabisBinding
import com.example.cannadex.utils.extensions.listen
import com.example.cannadex.utils.extensions.loadUrl

class StrainAdapter(
    private val listener: (Strain) -> Unit
) : RecyclerView.Adapter<StrainAdapter.StrainViewHolder>() {

    private val strains: MutableList<Strain> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemCannabisBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).let { StrainViewHolder(it).listen { position -> listener.invoke(strains[position]) } }

    override fun getItemCount() = strains.size

    override fun onBindViewHolder(holder: StrainViewHolder, position: Int) {
        holder.loadStrain(strains[position])
    }

    fun loadStrains(strains: List<Strain>) {
        this.strains.apply {
            clear()
            addAll(strains)
            notifyDataSetChanged()
        }
    }

    class StrainViewHolder(private val binding: ItemCannabisBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun loadStrain(strain: Strain) {
            binding.apply {
                ivCannabis.loadUrl(strain.image)
                tvCannabisName.text = strain.name?.trim()
            }
        }
    }
}
