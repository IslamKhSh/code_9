package com.devopsolution.code9.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devopsolution.code9.R
import com.devopsolution.code9.databinding.ItemCategoryBinding
import com.devopsolution.code9.databinding.ItemLanguageBinding
import com.devopsolution.code9.ui.activities.language.AvailableLang

class LanguagesAdapter(private val languages: List<AvailableLang>) :
    RecyclerView.Adapter<LanguagesAdapter.LanguageViewHolder>() {

    override fun getItemCount() = languages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding: ItemLanguageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_language, parent, false
        )

        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {

        languages[position].run {
            holder.binding.language = this

            holder.binding.root.setOnClickListener {
                val currentSelectedIndex = languages.indexOfFirst { it.isSelected }

                if (position == currentSelectedIndex)
                    return@setOnClickListener

                languages[currentSelectedIndex].isSelected = false
                notifyItemChanged(currentSelectedIndex)

                this.isSelected = true
                notifyItemChanged(position)
            }
        }
    }

    class LanguageViewHolder(val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root)
}