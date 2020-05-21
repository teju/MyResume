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

class MainFragment : BaseFragment() {

    val MAINFRAGMENT_LAYOUT = R.layout.main_fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(MAINFRAGMENT_LAYOUT, container, false)
        return v
    }
    override fun onBackTriggered() {
        home().proceedDoOnBackPressed()

    }
    private val mDelayedTransactionHandler = Handler()
    private val mRunnable = Runnable {
        val animation = AnimationUtils.loadAnimation(activity,
            R.anim.scale_up);

        white_bg.visibility = View.VISIBLE
        white_bg .setAnimation(animation);

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDelayedTransactionHandler.postDelayed(mRunnable, 1000);
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

    }

}
