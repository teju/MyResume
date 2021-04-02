package com.amazing.portfolio.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.portfolio.R
import com.amazing.portfolio.ui.adapters.FeaturesAdapter
import kotlinx.android.synthetic.main.fragment_features_two.*


class FeaturesFragmentTwo : Fragment() {
    private var animationDown: Animation? = null
    private var animationUp: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_features_two, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity!!)
        recyclerView.layoutManager = linearLayoutManager

        animationUp = AnimationUtils.loadAnimation(
            activity,
            R.anim.slide_up
        )
        animationDown = AnimationUtils.loadAnimation(
            activity,
            R.anim.slide_down
        )

        val recyclerViewAdapter = FeaturesAdapter(activity, animationUp, animationDown)
        recyclerView.adapter = recyclerViewAdapter
    }
}