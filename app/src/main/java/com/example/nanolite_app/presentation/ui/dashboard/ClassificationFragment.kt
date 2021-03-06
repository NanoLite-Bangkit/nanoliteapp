package com.example.nanolite_app.presentation.ui.dashboard

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nanolite_app.R
import com.example.nanolite_app.databinding.FragmentClassificationBinding
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.presentation.ui.HomeActivity
import com.example.nanolite_app.presentation.ui.detail.DetailWasteActivity
import com.example.nanolite_app.presentation.ui.detail.DetailWasteActivity.Companion.EXTRA_SCANNING_DATE
import com.example.nanolite_app.presentation.ui.detail.DetailWasteActivity.Companion.EXTRA_SCANNING_ID
import com.example.nanolite_app.presentation.ui.detail.DetailWasteActivity.Companion.EXTRA_WASTE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class ClassificationFragment : Fragment() {

    private val mInputSize = 224
    private val mModelPath = "model.tflite"
    private val mLabelPath = "labels.txt"
    private var classifier: Classifier? = null
    lateinit var img: Bitmap
    lateinit var path: String

    companion object{
        const val REQUEST_CODE = 100
        const val REQUEST_CAMERA = 123
    }

    private val classificationViewModel: ClassificationViewModel by viewModels()
    private lateinit var binding: FragmentClassificationBinding
    private lateinit var fos: OutputStream

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentClassificationBinding.inflate(layoutInflater)
        return binding.root
    }

    @Throws(IOException::class)
    private fun initClassifier(){
        classifier = Classifier(requireActivity().assets, mModelPath, mLabelPath, mInputSize)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCamera.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(view.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity as Activity, arrayOf(Manifest.permission.CAMERA), 777)
            } else {
                openCamera()
            }
        }
        binding.btnUploadImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE)
        }
        binding.btnPredict.setOnClickListener {
            val result: List<Classifier.Recognition> = classifier!!.recognizeImage(img)
            Toast.makeText(activity, result[0].toString(), Toast.LENGTH_SHORT).show()
            binding.textviewResult.setText(result[0].toString())
            binding.btnPredict.isEnabled = false
            predictAndResult(result[0].toString())
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null && data.data != null){

            binding.image.setImageURI(data.data)
            binding.textviewResult.setText(getString(R.string.predict_hint))
            binding.btnPredict.isEnabled = true

            //store image to bitmap for predict
            initClassifier()
            val uri: Uri?= data.data
            img = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
            path = saveImg(img)
        }

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CAMERA){
            val bitmap = data?.extras?.get("data") as Bitmap
            path = saveImg(bitmap)
            binding.image.setImageURI(Uri.parse(path))
            initClassifier()
            img = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, Uri.parse(path))

            val result: List<Classifier.Recognition> = classifier!!.recognizeImage(img)
            Toast.makeText(activity, result[0].toString(), Toast.LENGTH_SHORT).show()
            binding.textviewResult.setText(result[0].toString())

            predictAndResult(result[0].toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun predictAndResult(trash : String){
        val trashName = trash
        var classification = ""

        when(trashName){
            "Plastic" -> { classification = "anorganik"}
            "Cardboard" -> { classification = "anorganik"}
            "Glass" -> { classification = "anorganik"}
            "Metal" -> { classification = "anorganik"}
            "Paper" -> { classification = "anorganik"}
            "Trash" -> { classification = "organik"}
        }

        val email = classificationViewModel.getCurrentUser()?.email.toString()
        val date = LocalDateTime.now().toString()
        val waste = Waste( null, email,  trashName, date, path, classification)

        lifecycleScope.launch {
            classificationViewModel.insertScanningResult(waste)
        }

        //Intent ke DetailWasteActivity
        val intent = Intent(view?.context, DetailWasteActivity::class.java)
        intent.putExtra(EXTRA_WASTE, waste)
        intent.putExtra(EXTRA_SCANNING_DATE, date)
        intent.putExtra(EXTRA_SCANNING_ID, 1)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        view?.context?.startActivity(intent)
    }

    private fun saveImg(bitmap: Bitmap): String{
        var url: String = ""

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val resolver = view?.context?.contentResolver
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "${System.currentTimeMillis()}.jpg")
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)

            val imageUri = resolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            url = imageUri.toString()
            if (resolver != null) {
                fos = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
            }
        } else {
            val resolver = view?.context?.contentResolver

            val imageUri = MediaStore.Images.Media.insertImage(resolver, bitmap, "${System.currentTimeMillis()}.jpg", "${System.currentTimeMillis()}.jpg")
            url = imageUri.toString()
            if (resolver != null) {
                fos = resolver.openOutputStream(Objects.requireNonNull(Uri.parse(imageUri))!!)!!
            }
        }

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.flush()
        fos.close()

        return url
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 777 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            openCamera()
        }

    }

}