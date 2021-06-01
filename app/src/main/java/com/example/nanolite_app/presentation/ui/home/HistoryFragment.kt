package com.example.nanolite_app.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nanolite_app.databinding.FragmentHistoryBinding
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.utils.DataDummy
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private val historyViewModel: HistoryViewModel by viewModels()
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var adapter : HistoryAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryAdapter()

        val email = historyViewModel.getCurrentUser()?.email.toString()

        if (email != null) {
            historyViewModel.getHistory(email).observe(viewLifecycleOwner, { result ->
                adapter.setList(result as ArrayList<Waste>)
                binding.rvHistory.let {
                    it.layoutManager = LinearLayoutManager(view.context)
                    it.adapter = adapter
                }
            })
        }

    }

}