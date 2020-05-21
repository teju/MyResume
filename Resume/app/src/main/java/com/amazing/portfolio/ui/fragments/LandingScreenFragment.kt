package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.amazing.portfolio.R
import kotlinx.android.synthetic.main.fragment_landing_screen.*

class LandingScreenFragment : BaseFragment() ,View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id) {

        }
    }

    val banners: ArrayList<Int> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_landing_screen, container, false)
        return v
    }

    override fun onBackTriggered() {
        super.onBackTriggered()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_item_icon.setOnClickListener {
            val landingScreenFragment = MainFragment()
            home().setFragment(landingScreenFragment,R.anim.scale_up,R.animator.fragment_slide_right_exit)
        }
    }


}