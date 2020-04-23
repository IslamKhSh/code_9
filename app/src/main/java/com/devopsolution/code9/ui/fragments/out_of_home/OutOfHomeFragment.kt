package com.devopsolution.code9.ui.fragments.out_of_home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.devopsolution.code9.R
import com.devopsolution.code9.databinding.FragmentOutOfHomeBinding
import com.devopsolution.code9.ui.adapters.CategoriesAdapter
import com.devopsolution.code9.ui.base.BaseFragment

class OutOfHomeFragment : OutOfHomeView, BaseFragment<OutOfHomeViewModel, FragmentOutOfHomeBinding>(OutOfHomeViewModel::class.java) {

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun getLayoutRes() = R.layout.fragment_out_of_home

    override fun initViewModel(viewModel: OutOfHomeViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        initRecycler()
    }

    override fun initRecycler() {
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            mBinding.recyclerCategories.run {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                itemAnimator = DefaultItemAnimator()
                categoriesAdapter = CategoriesAdapter(it)
                adapter = categoriesAdapter
            }
        })
    }

    override fun refreshUI() {
        viewModel.categories.value = emptyList()
        super.refreshUI()
    }
}