package com.example.calculator

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.calculator.databinding.ActivitySlideBinding


class Slide : AppCompatActivity() {

    private lateinit var binding: ActivitySlideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        loadImagesInScrollView()
    }

    private fun getImages(): List<String> {
        return listOf(
            "https://cdn.mos.cms.futurecdn.net/7SfznToW65UX8QdS65Jn5d-1024-80.jpg",
            "https://http2.mlstatic.com/poster-originales-de-peliculas-de-cine-D_NQ_NP_100325-MLM25403772463_022017-F.jpg",
            "https://th.bing.com/th/id/R.024a9053ac2247fe747fe41dfa3600ef?rik=4DBhJWhbg37edQ&pid=ImgRaw&r=0",
            "https://th.bing.com/th/id/OIP.QkO72gzfHBErlRFsVc_-sgHaLH?rs=1&pid=ImgDetMain",
            "https://th.bing.com/th/id/OIP.B8Oz2FQKVsrcDB3ltvbB7gHaLH?rs=1&pid=ImgDetMain"
        )
    }

    private fun loadImagesInScrollView() {
        val imagesUrls:List<String> = getImages()
        for (imageUrl in imagesUrls) {
            val imageView = ImageView(this)
            val params = LinearLayout.LayoutParams( // set image layout properties
                400,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            imageView.apply { // set image properties
                layoutParams = params
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            Glide.with(this)
                .load(imageUrl)
                .override(400, 300) // set exact size
                .into(imageView)

            binding.LinearLayoutImages.addView(imageView)
        }
    }
}