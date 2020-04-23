package com.devopsolution.code9.ui.fragments.out_of_home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.devopsolution.code9.data.network.model.Category
import com.devopsolution.code9.ui.base.BaseViewModel

class OutOfHomeViewModel(app: Application) : BaseViewModel(app) {

    var categories = MutableLiveData<List<Category>>()
        get() {
            if (field.value.isNullOrEmpty()) {
                isLoading.value = true

                appRepositoryHelper.getCategories().observeForever {
                    handleResponseWhenError(it)

                    if (it.isResponseSuccessful)
                        field.postValue(it.responseBody!!.data)
                }
            }
            return field
        }


}