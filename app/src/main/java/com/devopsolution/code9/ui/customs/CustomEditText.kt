package com.devopsolution.code9.ui.customs

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.devopsolution.code9.R

class CustomEditText : AppCompatEditText {

    var isRequired = false
    var requiredErrorMsg: String? = null
    var validationType = 0 // 0 is none
    var validationErrorMsg: String? = null
    var mustMatched = -1 //for confirm password

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style) {
        init(attrs)
    }



    private fun init(attrs: AttributeSet?) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomMaterialEditText)

        isRequired =
            typedArray.getBoolean(R.styleable.CustomMaterialEditText_required, false)
        requiredErrorMsg = typedArray.getString(R.styleable.CustomMaterialEditText_required_error_msg)

        validationType = typedArray.getInt(R.styleable.CustomMaterialEditText_validation_type, 0)
        validationErrorMsg = typedArray.getString(R.styleable.CustomMaterialEditText_validation_error_msg)
        mustMatched = typedArray.getResourceId(R.styleable.CustomMaterialEditText_must_match, -1)

        typedArray.recycle()
    }

    fun validateContent(): Boolean {

        if (isRequired && this.text.isNullOrEmpty()) {
            if (requiredErrorMsg == null)
                this.error = "${context.resources.getString(R.string.empty_error_msg)} ${hint?: "".replace(
                    "*",
                    ""
                ).trim()}"
            else
                this.error = requiredErrorMsg

            return false
        } else if (!isRequired && this.text.isNullOrEmpty())
            return true

        when (validationType) {

            1 -> { // password
                if (text?.toString()?.length!! < 6) {
                    error = validationErrorMsg ?: context.getString(R.string.pass_validation_error_msg)
                    return false
                }
            }

        }

        return true
    }

}