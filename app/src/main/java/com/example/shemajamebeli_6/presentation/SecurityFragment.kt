package com.example.shemajamebeli_6.presentation

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shemajamebeli_6.BaseFragment
import com.example.shemajamebeli_6.R
import com.example.shemajamebeli_6.databinding.FragmentSecurityBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SecurityFragment : BaseFragment<FragmentSecurityBinding>(FragmentSecurityBinding::inflate) {

    private val viewModel: SecurityViewModel by viewModels()


    override fun bind() {

    }

    override fun listener() {
        addListener()
        clearListener()
    }

    override fun observe() {
        securityObserve()
        verifyObserve()
    }


    private fun securityObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.securityStateState.collect {
                    bindPasscodeIcon(it)
                }
            }
        }
    }

    private fun verifyObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.verifyState.collect {
                    verify(it)
                }
            }
        }
    }

    private fun verify(itVerify: Boolean) {
        if (itVerify)
            Toast.makeText(context, "Is Verify", Toast.LENGTH_SHORT).show()
    }

    private fun bindPasscodeIcon(list: List<Int>) {
        with(binding) {
            if (list.isNotEmpty()) {
                ivIconOne.setImageResource(R.drawable.ic_green_circle)
            } else {
                ivIconOne.setImageResource(R.drawable.ic_gray_circle)
                return
            }
            if (list.size > 1) {
                ivIconTwo.setImageResource(R.drawable.ic_green_circle)
            } else {
                ivIconTwo.setImageResource(R.drawable.ic_gray_circle)
                return
            }
            if (list.size > 2) {
                ivIconThree.setImageResource(R.drawable.ic_green_circle)
            } else {
                ivIconThree.setImageResource(R.drawable.ic_gray_circle)
                return
            }
            if (list.size > 3) {
                ivIconFour.setImageResource(R.drawable.ic_green_circle)
                viewModel.onEvent(SecurityEvent.VerifyPasscode)
                clearPasscodeIcon()
            } else {
                ivIconFour.setImageResource(R.drawable.ic_gray_circle)
            }
        }
    }

    private fun clearListener() {
        binding.ibClear.setOnClickListener {
            viewModel.onEvent(SecurityEvent.ClearPasscode)
        }
    }

    private fun clearPasscodeIcon() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(100)

            with(binding) {
                ivIconOne.setImageResource(R.drawable.ic_gray_circle)
                ivIconTwo.setImageResource(R.drawable.ic_gray_circle)
                ivIconThree.setImageResource(R.drawable.ic_gray_circle)
                ivIconFour.setImageResource(R.drawable.ic_gray_circle)
            }
        }

    }

    private fun addListener() {
        with(binding) {
            acbOne.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(1))
            }
            acbTwo.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(2))
            }
            acbThree.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(3))
            }
            acbFour.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(4))
            }
            acbFive.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(5))
            }
            acbSix.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(6))
            }
            acbSeven.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(7))
            }
            acbEight.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(8))
            }
            acbNine.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(9))
            }
            acbZero.setOnClickListener {
                viewModel.onEvent(SecurityEvent.AddPasscode(0))
            }
        }
    }
}