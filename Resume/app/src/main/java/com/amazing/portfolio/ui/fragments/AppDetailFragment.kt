package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazing.portfolio.R
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import androidx.databinding.adapters.ViewGroupBindingAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import android.widget.RelativeLayout
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight
import android.os.Handler
import android.view.animation.Animation.AnimationListener
import com.amazing.portfolio.etc.Helper
import kotlinx.android.synthetic.main.app_detail_fragment.*
import java.lang.Exception

class AppDetailFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.app_detail_fragment, container, false)
        return v
    }


    private val mDelayedTransactionHandler = Handler()
    private val mRunnable = Runnable {
        try {
            val animation = AnimationUtils.loadAnimation(
                activity,
                R.anim.scale_up
            );

            white_bg.visibility = View.VISIBLE
            white_bg.setAnimation(animation);
        } catch (e : Exception){

        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDelayedTransactionHandler.postDelayed(mRunnable, 1000);
    }

}
