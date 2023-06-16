package com.candroid.candroidrcc026

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.candroid.candroidrcc026.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Settings"
        binding.group.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.jp_rb -> changeCountry("jp")
                R.id.us_rb -> changeCountry("us")
            }
        }
    }
    private fun changeCountry(code: String) {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE).edit()
        prefs.putString("code", code)
        prefs.apply()
    }

}