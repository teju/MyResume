package com.amazing.portfolio.ui.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import com.amazing.portfolio.R
import kotlinx.android.synthetic.main.fragment_landing_screen.*
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.amazing.portfolio.etc.Helper
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MenuScreenFragment(
    var b: Boolean = true,
    var mainFragment: MainFragment?
) : BaseFragment() ,View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_landing_screen, container, false)
        return v
    }

    override fun onResume() {
        super.onResume()

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val runnable = object : Runnable {
                override fun run() {
                    try {
                        planet1.animate().rotationBy(360F).withEndAction(this).setDuration(8000)
                            .setInterpolator(LinearInterpolator()).start()
                        planet2.animate().rotationBy(-360F).withEndAction(this).setDuration(8000)
                            .setInterpolator(LinearInterpolator()).start()
                        planet3.animate().rotationBy(360F).withEndAction(this).setDuration(8000)
                            .setInterpolator(LinearInterpolator()).start()
                        planet4.animate().rotationBy(-360F).withEndAction(this).setDuration(8000)
                            .setInterpolator(LinearInterpolator()).start()
                        planet5.animate().rotationBy(360F).withEndAction(this).setDuration(8000)
                            .setInterpolator(LinearInterpolator()).start()
                    } catch (e:Exception) {

                    }

                }
            }
            planet1.animate().rotationBy(360F).withEndAction(runnable).setDuration(8000)
                .setInterpolator(LinearInterpolator()).start()
            planet2.animate().rotationBy(-360F).withEndAction(runnable).setDuration(8000)
                .setInterpolator(LinearInterpolator()).start()
            planet3.animate().rotationBy(360F).withEndAction(runnable).setDuration(8000)
                .setInterpolator(LinearInterpolator()).start()
            planet4.animate().rotationBy(-360F).withEndAction(runnable).setDuration(8000)
                .setInterpolator(LinearInterpolator()).start()
            planet5.animate().rotationBy(360F).withEndAction(runnable).setDuration(8000)
                .setInterpolator(LinearInterpolator()).start()
        } catch (e:Exception) {

        }


        planet1.setOnClickListener(this)
        planet2.setOnClickListener(this)
        planet3.setOnClickListener(this)
        planet4.setOnClickListener(this)
        planet5.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.planet1 -> {
                animate(planet1,0)
            }
            R.id.planet2 -> {
                animate(planet2,1)
            }
            R.id.planet3 -> {
                animate(planet3,2)
            }
            R.id.planet4 -> {
                animate(planet4,3)
            }
            R.id.planet5 -> {
                animate(planet5,4)
            }
        }
    }

    fun animate(planet : ImageView,pos : Int) {
        val myAnim = AnimationUtils.loadAnimation(activity!!, R.anim.bounce);
        planet.startAnimation(myAnim);
        Timer("SettingUp", false).schedule(700) {
            if(b) {
                home().setFragment(MainFragment().apply {
                    instance = pos
                })
            } else {
                mainFragment?.instance = pos
                mainFragment?.setCurrentItem(pos)
                mainFragment?.showTab()
            }
        }

    }
}