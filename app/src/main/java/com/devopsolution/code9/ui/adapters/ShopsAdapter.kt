package com.devopsolution.code9.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.devopsolution.code9.R
import com.devopsolution.code9.data.network.model.Category
import com.devopsolution.code9.data.network.model.Shop
import com.devopsolution.code9.databinding.ItemCategoryBinding
import com.devopsolution.code9.databinding.ItemShopBinding
import com.devopsolution.code9.ui.fragments.out_of_home.OutOfHomeFragmentDirections
import com.google.android.material.shape.CornerFamily

class ShopsAdapter(private val shops: List<Shop>, private val icon: String?) :
    RecyclerView.Adapter<ShopsAdapter.ShopViewHolder>() {

    override fun getItemCount() = shops.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding: ItemShopBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_shop, parent, false
        )

        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {

        shops[position].run {
            holder.binding.shop = this
            holder.binding.icon = icon

            holder.binding.imgBanner.run {
                val radius = context.resources.getDimension(R.dimen.x25dp)
                shapeAppearanceModel = shapeAppearanceModel
                    .toBuilder()
                    .setTopRightCorner(CornerFamily.ROUNDED, radius)
                    .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                    .build()
            }

            holder.binding.root.setOnClickListener {
                val action  = OutOfHomeFragmentDirections.actionOutOfHomeToBarCode(this, icon)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    class ShopViewHolder(val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root)
}