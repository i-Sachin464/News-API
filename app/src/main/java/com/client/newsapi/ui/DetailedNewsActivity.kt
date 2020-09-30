package com.client.newsapi.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.client.newsapi.R
import com.client.newsapi.model.News
import com.squareup.picasso.Picasso


class DetailedNewsActivity : AppCompatActivity() {
    private val context = this@DetailedNewsActivity

    lateinit var title: TextView
    lateinit var description: TextView
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_news)
        init()
        if (intent.extras != null) {
            val extras = intent.getParcelableExtra<News>("newsExtra")
            if (extras != null) {
                title.text = extras.title
                description.text = extras.description
                val imageUrl: String? = extras.urlToImage
//                imageView.setImageResource(R.mipmap.ic_launcher)
                Picasso.with(context).load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher_round)
                    .into(imageView);

                imageView.setOnClickListener {

                }
            }
        }
    }

    private fun init() {
        title = findViewById(R.id.title)
        description = findViewById(R.id.description)
        imageView = findViewById(R.id.imageview)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}