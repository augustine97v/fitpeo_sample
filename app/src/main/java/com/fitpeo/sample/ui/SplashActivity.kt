package com.fitpeo.sample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.fitpeo.sample.R
import com.fitpeo.sample.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace)
        binding.idSplashLogo.startAnimation(hyperspaceJump)
        val activityScope = CoroutineScope(Dispatchers.Main)


        activityScope.launch {
            delay(3000)


            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)


            finish()
        }
    }
}