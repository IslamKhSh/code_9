package com.devopsolution.code9.ui.fragments.check_in

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devopsolution.code9.R
import com.devopsolution.code9.databinding.FragmentCheckInBinding
import com.devopsolution.code9.ui.base.BaseFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator


class CheckInFragment : CheckInView,
    BaseFragment<CheckInViewModel, FragmentCheckInBinding>(CheckInViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_check_in

    override fun initViewModel(viewModel: CheckInViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        initScanner()

        mBinding.btnConfirm.setOnClickListener {
            viewModel.checkInUser().observe(viewLifecycleOwner, Observer {
                if (it.isResponseSuccessful)
                    findNavController().popBackStack(R.id.home_fragment, false)
            })
        }
    }

    override fun initScanner() {

        IntentIntegrator(activity)
            .setOrientationLocked(false)
            .setBarcodeImageEnabled(true)
            .setBeepEnabled(false)
            .setPrompt(getString(R.string.capture_qrcode_prompt))
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
            .setBarcodeImageEnabled(false)
            .initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (scanResult != null) {
            viewModel.getUserInfo(scanResult.contents)
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }
}