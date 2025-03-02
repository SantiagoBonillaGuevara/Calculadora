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
        initUI()
    }

    private fun initUI() {
        loadImagesInScrollView()
    }

    private fun loadImagesInScrollView() {
        val imagesUrls: List<String> = ImageCollection.defaultImages
        for (index in imagesUrls.indices) {
            val imageUrl = imagesUrls[index]

            when (index) {
                0 -> binding.LinearLayoutImages1.addView(createImageView(imageUrl))
                in 1..4 -> {
                    binding.LinearLayoutImages1.addView(createImageView(imageUrl))
                    binding.LinearLayoutImages2.addView(createImageView(imageUrl))
                }

                in 5..8 -> binding.LinearLayoutImages2.addView(createImageView(imageUrl))
                in 9..14 -> binding.LinearLayoutImages3.addView(createImageView(imageUrl))
            }
        }
    }

    fun createImageView(imageUrl: String): ImageView {
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

        return imageView
    }
}