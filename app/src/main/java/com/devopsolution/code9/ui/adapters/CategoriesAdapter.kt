package com.devopsolution.code9.ui.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devopsolution.code9.R
import com.devopsolution.code9.data.network.model.Category
import com.devopsolution.code9.databinding.ItemCategoryBinding
import com.devopsolution.code9.ui.fragments.out_of_home.OutOfHomeView
import java.util.*

class CategoriesAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun getItemCount() = categories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding: ItemCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_category, parent, false
        )

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        categories[position].run {
            holder.binding.category = this
            holder.binding.recyclerShops.let {
                it.layoutManager =
                    LinearLayoutManager(it.context, LinearLayoutManager.HORIZONTAL, false)
                it.setHasFixedSize(true)
                it.adapter = ShopsAdapter(shops, icon)
            }
        }
    }

    class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}