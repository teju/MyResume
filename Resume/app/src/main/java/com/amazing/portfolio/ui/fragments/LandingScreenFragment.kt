package com.amazing.portfolio.ui.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import com.amazing.portfolio.R
import kotlinx.android.synthetic.main.fragment_landing_screen.*

class LandingScreenFragment : BaseFragment() ,View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id) {

        }
    }

    private var mEarthButtonAnimation: AnimationDrawable? = null
    val banners: ArrayList<Int> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_landing_screen, container, false)
        return v
    }

    override fun onResume() {
        super.onResume()
        mEarthButtonAnimation?.start()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mEarthButtonAnimation = earth_button.getBackground() as AnimationDrawable
        earth_button.setOnClickListener {
            mEarthButtonAnimation?.stop()
            val myAnim = AnimationUtils.loadAnimation(activity!!, R.anim.bounce);
            earth_button.startAnimation(myAnim);
            myAnim.setAnimationListener(object : Animation.AnimationListener {

                override fun onAnimationStart(animation: Animation) {}

                override fun onAnimationRepeat(animation: Animation) {}

                override fun onAnimationEnd(animation: Animation) {
                    home().setFragment(MainFragment())
                }
            })
        }
    }

    override fun onDetach() {
        super.onDetach()
        mEarthButtonAnimation?.stop()
    }



}