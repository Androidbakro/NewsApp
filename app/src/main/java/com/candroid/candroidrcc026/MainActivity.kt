package com.candroid.candroidrcc026

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import com.candroid.candroidrcc026.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadNews()

        binding.refresh.setOnRefreshListener { loadNews() }

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

    }

    private fun loadNews(){
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cat = intent.getStringExtra("cat") //sports

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val code = prefs.getString("code", "us")

        val callable = retrofit.create(Callable::class.java)
        callable.getNews(cat!!, code!!).enqueue(object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val articles = news?.articles
                //Log.d("trace", "Link: ${articles?.get(0)?.urlToImage}")
                val adapter = NewsAdapter(this@MainActivity, articles!!)
                binding.newsRv.adapter = adapter
                binding.refresh.isRefreshing = false
                binding.progress.visibility = View.GONE
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {

            }
        })

    }

}
