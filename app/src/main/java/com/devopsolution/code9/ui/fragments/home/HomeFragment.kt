package com.devopsolution.code9.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devopsolution.code9.R
import com.devopsolution.code9.databinding.FragmentHomeBinding
import com.devopsolution.code9.ui.base.BaseFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

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

    }
}