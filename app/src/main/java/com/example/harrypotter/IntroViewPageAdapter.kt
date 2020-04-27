package com.example.harrypotter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView

class IntroViewPageAdapter(val mContext: Context, var mListSreen: List<ScreenItem>): PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mListSreen.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)  //Here is different from the source code
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var layoutScreen: View = inflater.inflate(R.layout.layout_screen, null)

        var animationSlide: LottieAnimationView = layoutScreen.findViewById(R.id.intro_animation)
        var title: TextView = layoutScreen.findViewById(R.id.intro_title)
        var description: TextView = layoutScreen.findViewById(R.id.intro_description)


        animationSlide.setAnimation(mListSreen[position].AnimationFile)
        title.setText(mListSreen[position].Title)
        description.setText(mListSreen[position].Description)

        container.addView(layoutScreen)

        return layoutScreen
    }


}