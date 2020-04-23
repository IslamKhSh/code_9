package com.devopsolution.code9.ui.dialogs.bar_code

import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.devopsolution.code9.R
import com.devopsolution.code9.common.utils.DimenConverter
import com.devopsolution.code9.common.utils.DimenConverter.convertDpToPixel
import com.devopsolution.code9.databinding.DialogBarCodeBinding
import com.devopsolution.code9.ui.base.BaseDialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.Writer
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder


class BarCodeDialogFragment : BarCodeView,
    BaseDialogFragment<BarCodeViewModel, DialogBarCodeBinding>(BarCodeViewModel::class.java) {

    val args by navArgs<BarCodeDialogFragmentArgs>()

    override fun getLayoutRes() = R.layout.dialog_bar_code


    override fun initViewModel(viewModel: BarCodeViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        viewModel.shop.value = args.shop
        viewModel.categoryIcon.value = args.icon

        initBarCode()
    }

    override fun initBarCode() {

        val multiFormatWriter = MultiFormatWriter()

        val dimen = context?.resources?.getDimensionPixelOffset(R.dimen.bar_code_size) ?: 0

        val bitMatrix =
            multiFormatWriter.encode(viewModel.storedUserId, BarcodeFormat.QR_CODE, dimen, dimen)
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.createBitmap(bitMatrix)
        mBinding.imgBarCode.setImageBitmap(bitmap)
    }
}