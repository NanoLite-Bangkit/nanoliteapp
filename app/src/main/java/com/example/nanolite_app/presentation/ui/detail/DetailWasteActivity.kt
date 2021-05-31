package com.example.nanolite_app.presentation.ui.detail

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.nanolite_app.databinding.ActivityDetailWasteBinding
import com.example.nanolite_app.domain.model.Recycler
import com.example.nanolite_app.domain.model.Waste
import com.example.nanolite_app.utils.DataDummy
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.File

class DetailWasteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailWasteBinding
    private lateinit var waste: Waste
    private lateinit var adapter: RecommendationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if(extra != null){
            waste = extra.getParcelable<Waste>(EXTRA_WASTE) as Waste
        }

        adapter = RecommendationAdapter()

        val list  = ArrayList<Recycler>()

        for(rekomen in DataDummy.getRecommendation()){
            if(rekomen.jenisSampah == waste.trashName){
                list.add(rekomen)
            }
        }

        adapter.setList(list)

        binding.let {
            it.tvDate.text = waste.date
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

        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 220
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }


    }

    companion object{
        const val EXTRA_WASTE = "extra_waste"
    }
}