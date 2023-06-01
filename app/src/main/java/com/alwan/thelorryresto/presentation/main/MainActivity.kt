package com.alwan.thelorryresto.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alwan.thelorryresto.R
import com.alwan.thelorryresto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}