package com.example.nanolite_app.presentation.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nanolite_app.R
import com.example.nanolite_app.databinding.FragmentClassificationBinding
import java.io.IOException

class ClassificationFragment : Fragment() {

    private val mInputSize = 224
    private val mModelPath = "model.tflite"
    private val mLabelPath = "labels.txt"
    private var classifier: Classifier? = null
    lateinit var img: Bitmap

    companion object{
        const val REQUEST_CODE = 100
    }

    private lateinit var classificationViewModel: ClassificationViewModel
    private lateinit var binding: FragmentClassificationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        classificationViewModel =
                ViewModelProvider(this).get(ClassificationViewModel::class.java)
        binding = FragmentClassificationBinding.inflate(layoutInflater)

        try {
            initClassifier()
            binding.btnUploadImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.setType("image/*")
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE)
            }
            binding.btnPredict.setOnClickListener {
                val result: List<Classifier.Recognition> = classifier!!.recognizeImage(img)
                Toast.makeText(activity, result[0].toString(), Toast.LENGTH_SHORT).show()
                binding.textviewResult.setText(result[0].toString())
                binding.btnRecommendation.visibility = View.VISIBLE
                binding.btnPredict.isEnabled = false

            }
            binding.btnRecommendation.setOnClickListener {
                //ke halaman yg sama kayak detail history
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return binding.root
    }

    @Throws(IOException::class)
    private fun initClassifier(){
        classifier = Classifier(requireActivity().assets, mModelPath, mLabelPath, mInputSize)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.btnUploadImage.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.setType("image/*")
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE)
//        }
//    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null && data.data != null){

            binding.image.setImageURI(data.data)
            binding.textviewResult.setText(getString(R.string.predict_hint))
            binding.btnPredict.isEnabled = true
            binding.btnRecommendation.visibility = View.INVISIBLE

            //store image to bitmap for predict
            var uri: Uri?= data?.data
            img = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
        }
    }
}