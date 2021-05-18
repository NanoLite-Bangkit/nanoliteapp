package com.example.nanolite_app.presentation.ui.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nanolite_app.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        accountViewModel =
                ViewModelProvider(this).get(AccountViewModel::class.java)

        binding = FragmentAccountBinding.inflate(layoutInflater)


        accountViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })

        return binding.root
    }
}