package com.devopsolution.code9.ui.fragments.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devopsolution.code9.R
import com.devopsolution.code9.common.extensions.showDialog
import com.devopsolution.code9.databinding.FragmentHomeBinding
import com.devopsolution.code9.ui.base.BaseFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.zxing.integration.android.IntentIntegrator

class HomeFragment : HomeView,
    BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_home


    override fun initViewModel(viewModel: HomeViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        viewModel.getUserDashboard()
        initFcmToken()

        mBinding.containerHomeMainAction.setOnClickListener {
            findNavController().navigate(R.id.outOfHome_fragment)
        }

        viewModel.notActiveFeatureClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.notActiveFeatureClicked.value = false
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
        })

        mBinding.containerCheckIn.root.setOnClickListener {
            findNavController().navigate(R.id.checkInFragment)
        }

        initCheckOut()
    }

    @SuppressLint("HardwareIds")
    override fun initFcmToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task: Task<InstanceIdResult> ->
                if (!task.isSuccessful)
                    return@addOnCompleteListener

                val deviceId = Settings.Secure.getString(
                    requireContext().contentResolver,
                    Settings.Secure.ANDROID_ID
                )

                // Get new Instance ID token
                viewModel.setNotificationToken(task.result!!.token, deviceId)
            }
    }

    override fun initCheckOut() {

        mBinding.containerCheckOut.root.setOnClickListener {
            IntentIntegrator(activity)
                .setOrientationLocked(false)
                .setBarcodeImageEnabled(true)
                .setBeepEnabled(false)
                .setPrompt(getString(R.string.capture_qrcode_prompt))
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                .setBarcodeImageEnabled(false)
                .initiateScan()
        }

    }

    override fun checkout(userId: String) {

        viewModel.checkout(userId).observe(viewLifecycleOwner, Observer {
            if (it.isResponseSuccessful)
                showDialog(getString(R.string.check_out_successfuly), {})

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (scanResult != null) {
            checkout(scanResult.contents)
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }
}