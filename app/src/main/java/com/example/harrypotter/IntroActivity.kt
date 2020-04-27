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
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class IntroActivity : AppCompatActivity() {

    private lateinit var screenPager: ViewPager
    lateinit var introViewPageAdapter: IntroViewPageAdapter
    lateinit var tabIndicator: TabLayout
    lateinit var nextButton: Button
    lateinit var getStartedButton: Button
    lateinit var getStartedButtonAnimation: Animation
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)


        // When this activity is about to be launch we need to check if its opened before or not

        if (isIntroPageOpened()){

            var mainActivity: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }


        setContentView(R.layout.activity_intro)

        // hide the action bar

        supportActionBar?.hide()

        // init views
        nextButton = findViewById(R.id.button_next)
        getStartedButton = findViewById(R.id.button_get_started)
        tabIndicator = findViewById(R.id.tab_indicator)
        getStartedButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        // Fill list screen

        var mList: List<ScreenItem> = listOf(
            ScreenItem("Harry Potter Library", "Hello, welcome to the Harry Potter Library mobile application, here you can find a lot about the Harry Potter Universe", "hello_wizard.json"),
            ScreenItem("Characters", "Get details about every single characters in the book series, as school name, house and specie.", "hermione.json"),
            ScreenItem("Spells", "Know about all the spells used in the series, discover new ones and get information about them.", "wizard-spells.json")
        )

        //Setup viewpager
        screenPager = findViewById(R.id.screen_viewpager)
        introViewPageAdapter = IntroViewPageAdapter(this, mList)
        screenPager.adapter = introViewPageAdapter

        //setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager)

        // nest button click listener

        nextButton.setOnClickListener {

            position = screenPager.currentItem
            if (position < mList.size){

                position++
                screenPager.currentItem = position

            }

            if(position == mList.size){
                // TODO: show the getStarted button and hide the indicator and the next button
                loadLastScreen()
            }

        }

        // tablayout add change listener

        tabIndicator.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {
                if(tab.position == mList.size - 1){
                    loadLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        //Get Started button click listener

        getStartedButton.setOnClickListener {

            //open main acitivity
            var mainActivity: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)

            // Also we need to save a boolean value to storage so next time when the user run the app
            // we could know that he is already checked the intro screen
            // We're going to use shared preferences to that process

            savePreferencesData()
            finish()

        }


    }

    private fun isIntroPageOpened(): Boolean{
        return applicationContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE).getBoolean("isIntroPageOpened", false)
    }

    private fun savePreferencesData() {
        var preferences: SharedPreferences = applicationContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        var preferencesEditor: SharedPreferences.Editor = preferences.edit()
        preferencesEditor.putBoolean("isIntroPageOpened", true)
        preferencesEditor.commit()
    }

    private fun loadLastScreen() {
        // TODO: show the getStarted button and hide the indicator and the next button
        nextButton.visibility = View.INVISIBLE
        getStartedButton.visibility = View.VISIBLE
        tabIndicator.visibility = View.INVISIBLE

        //TODO: Add an animation the getStarted button
        // setup animation
        getStartedButton.animation = getStartedButtonAnimation

    }
}
