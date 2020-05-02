package com.example.harrypotter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.harrypotter.databinding.ActivityIntroBinding
import com.google.android.material.tabs.TabLayout

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        // When this activity is about to be launch we need to check if its opened before or not

        if (isIntroPageOpened()){

            var mainActivity: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }

        val binding = DataBindingUtil.setContentView<ActivityIntroBinding>(this,R.layout.activity_intro)

        // hide the action bar

        supportActionBar?.hide()
    }
    private fun isIntroPageOpened(): Boolean{
        return applicationContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE).getBoolean("isIntroPageOpened", false)
    }

}
