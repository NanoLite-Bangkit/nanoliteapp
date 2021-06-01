package com.example.nanolite_app.presentation.ui.detail

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nanolite_app.databinding.ActivityDetailWasteBinding
import com.example.nanolite_app.domain.model.Recycler
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.presentation.ui.HomeActivity
import com.example.nanolite_app.utils.DataDummy
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.api.LogDescriptor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailWasteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailWasteBinding
    private lateinit var waste: Waste
    private lateinit var adapter: RecommendationAdapter
    private val viewModel: DetailWasteViewModel by viewModels()
    private var date: String = ""
    private lateinit var list: ArrayList<Recycler>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RecommendationAdapter()
        list = ArrayList<Recycler>()

        getDataIntent()

        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 220
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    fun getDataIntent(){
        val extra = intent.extras
        if(extra != null){
            val id: Int = extra.getInt(EXTRA_SCANNING_ID, 0)
            if(id == SCANNING_ID){
                date = intent.getStringExtra(EXTRA_SCANNING_DATE).toString()

                viewModel.getScanningResult(date).observe(this, { wastes ->
                    waste = wastes[0]
                    list = viewModel.getRecommendation(waste.trashName)
                    adapter.setList(list)

                    bind(waste)
                })

            } else {
                waste = extra.getParcelable<Waste>(EXTRA_WASTE) as Waste

                list = viewModel.getRecommendation(waste.trashName)
                adapter.setList(list)
                bind(waste)
            }

        }
    }


    private fun bind(waste: Waste){

        binding.let {
            it.tvDate.text = formatDate(waste.date)
            it.tvWasteName.text = waste.trashName
            it.tvClassification.text = waste.classification

            Glide.with(binding.root)
                    .load(Uri.parse(waste.imageUri))
                    .centerCrop()
                    .into(binding.ivWaste)

            it.rvRekomendasi.let { rv ->
                rv.layoutManager = LinearLayoutManager(this)
                rv.adapter = adapter
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String{
        var formattedDate = ""
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val localDateTime = LocalDateTime.parse(date)
            val dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy - h:mm a")
            formattedDate = dateFormatter.format(localDateTime)
        } else {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("EEE, d MMM yyyy - h:mm a")
            formattedDate = formatter.format(parser.parse(date))
        }
        return formattedDate
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    companion object{
        const val EXTRA_WASTE = "extra_waste"

        const val EXTRA_SCANNING_DATE = "scanning_date"
        const val EXTRA_SCANNING_ID = "scanning_id"
        const val SCANNING_ID = 1
    }
}