package com.example.harrypotter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.harrypotter.databinding.FragmentIntroductionBinding
import com.google.android.material.tabs.TabLayout
class IntroductionFragment : Fragment() {

    var positon: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding: FragmentIntroductionBinding =  DataBindingUtil.inflate(
           inflater, R.layout.fragment_introduction, container, false
       )

        var mList: List<ScreenItem> = listOf(
            ScreenItem("Harry Potter Library", "Hello, welcome to the Harry Potter Library mobile application, here you can find a lot about the Harry Potter Universe", "hello_wizard.json"),
            ScreenItem("Characters", "Get details about every single characters in the book series, as school name, house and specie.", "hermione.json"),
            ScreenItem("Spells", "Know about all the spells used in the series, discover new ones and get information about them.", "wizard-spells.json")
        )

       binding.screenViewpager.adapter = IntroViewPageAdapter(requireContext().applicationContext, mList)

       binding.tabIndicator.setupWithViewPager(binding.screenViewpager)

       binding.buttonNext.setOnClickListener {
           positon = binding.screenViewpager.currentItem
           if (positon < mList.size){

               positon++
               binding.screenViewpager.currentItem = positon

           }

           if(positon == mList.size){
               // TODO: show the getStarted button and hide the indicator and the next button
               loadLastScreen(binding)
           }

       }

       binding.tabIndicator.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{

           override fun onTabSelected(tab: TabLayout.Tab) {
               if(tab.position == mList.size - 1){
                   loadLastScreen(binding)
               }
           }

           override fun onTabUnselected(tab: TabLayout.Tab) {

           }

           override fun onTabReselected(tab: TabLayout.Tab) {

           }

       })
        binding.buttonGetStarted.setOnClickListener {

            //open main acitivity
            var mainActivity: Intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(mainActivity)

            // Also we need to save a boolean value to storage so next time when the user run the app
            // we could know that he is already checked the intro screen
            // We're going to use shared preferences to that process

            savePreferencesData()
            requireActivity().finish()
        }
        return binding.root
    }

    private fun savePreferencesData() {
        var preferences: SharedPreferences = requireContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        var preferencesEditor: SharedPreferences.Editor = preferences.edit()
        preferencesEditor.putBoolean("isIntroPageOpened", true)
        preferencesEditor.commit()
    }

    private fun loadLastScreen(binding: FragmentIntroductionBinding) {
        // TODO: show the getStarted button and hide the indicator and the next button
        binding.buttonNext.visibility = View.INVISIBLE
        binding.buttonGetStarted.visibility = View.VISIBLE
        binding.tabIndicator.visibility = View.INVISIBLE

        //TODO: Add an animation the getStarted button
        // setup animation
        binding.buttonGetStarted.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.button_animation)
    }
}
