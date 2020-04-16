package com.example.harrypotter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class IntroActivity : AppCompatActivity() {

    private lateinit var screenPager: ViewPager
    lateinit var introViewPageAdapter: IntroViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // Fill list screen

        var mList: List<ScreenItem> = listOf(
            ScreenItem("Fresh Food", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", R.drawable.img1),
            ScreenItem("Fast Delivery", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", R.drawable.img2),
            ScreenItem("Easy Payment", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", R.drawable.img3)
        )

        //Setup viewpager
        screenPager = findViewById(R.id.screen_viewpager)
        introViewPageAdapter = IntroViewPageAdapter(this, mList)
        screenPager.adapter = introViewPageAdapter
    }
}
