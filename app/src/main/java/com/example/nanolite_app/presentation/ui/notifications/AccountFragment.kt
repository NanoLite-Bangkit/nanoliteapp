package com.example.nanolite_app.presentation.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nanolite_app.databinding.FragmentAccountBinding
import com.example.nanolite_app.presentation.ui.signup.MainActivity
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val accountViewModel: AccountViewModel by viewModels()
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                accountViewModel.signOut()
                val intent = Intent(view.context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                view.context.startActivity(intent)
            }
        }

        val email = accountViewModel.getCurrentUser()?.email.toString()
        initView(accountViewModel.getUserData(email))

    }

    private fun initView(document: DocumentReference) {
        document.addSnapshotListener { value, error ->
            if (value != null){
                binding.tvEmail.text = value["email"].toString()
                binding.tvNama.text = value["name"].toString()
            }
            else Log.e("Account_Fragment", error?.message.toString())
        }
    }

}