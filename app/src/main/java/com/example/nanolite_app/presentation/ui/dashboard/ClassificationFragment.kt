package com.example.nanolite_app.presentation.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nanolite_app.databinding.FragmentClassificationBinding

class ClassificationFragment : Fragment() {
    companion object{
        const val REQUEST_CODE = 100
    }

    private lateinit var classificationViewModel: ClassificationViewModel
    private lateinit var binding: FragmentClassificationBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        classificationViewModel =
                ViewModelProvider(this).get(ClassificationViewModel::class.java)
        binding = FragmentClassificationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnUploadImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null && data.data != null){

            binding.image.setImageURI(data.data)

            val filePath = data.data
        }
    }
}