package com.devopsolution.code9.ui.dialogs.bar_code

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.data.network.model.Category
import com.devopsolution.code9.data.network.model.Shop
import com.devopsolution.code9.ui.base.BaseViewModel

class BarCodeViewModel(app : Application) : BaseViewModel(app) {

    val shop = MutableLiveData<Shop>()
    val categoryIcon = MutableLiveData<String>()
}