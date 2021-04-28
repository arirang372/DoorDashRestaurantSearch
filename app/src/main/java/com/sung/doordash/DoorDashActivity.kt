package com.sung.doordash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sung.doordash.databinding.DoordashActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoorDashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<DoordashActivityBinding>(this, R.layout.doordash_activity)
    }
}