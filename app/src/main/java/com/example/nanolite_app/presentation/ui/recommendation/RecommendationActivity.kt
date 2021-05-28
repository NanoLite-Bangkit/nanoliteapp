package com.example.nanolite_app.presentation.ui.recommendation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.nanolite_app.databinding.ActivityRecommendationBinding
import com.example.nanolite_app.domain.model.Recycler

class RecommendationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecommendationBinding
    private lateinit var recycler: Recycler

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if(extra != null){
            recycler = extra.getParcelable<Recycler>(EXTRA_RECYCLER) as Recycler
        }

        binding.let {
            it.webView.loadUrl(recycler.link)
            it.webView.settings.javaScriptEnabled = true
            it.webView.webViewClient = WebViewClient()
        }

    }

    companion object{
        const val EXTRA_RECYCLER = "extra_recycler"
    }
}