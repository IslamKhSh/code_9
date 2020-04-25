package com.devopsolution.code9.ui.activities.language

import android.app.Application
import com.devopsolution.code9.ui.base.BaseViewModel

class LanguageViewModel(app: Application) : BaseViewModel(app) {

    val availableLangList = listOf(
        AvailableLang("English", true),
        AvailableLang("Arabic - عربي"),
        AvailableLang("Hindi - हिन्दी"),
        AvailableLang("Russian - русский язык"),
        AvailableLang("Chinese - 漢語")
    )
}

data class AvailableLang(
    val name: String,
    var isSelected: Boolean = false
)