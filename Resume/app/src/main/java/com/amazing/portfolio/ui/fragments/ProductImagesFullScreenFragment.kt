package com.amazing.portfolio.ui.fragments

import android.animation.LayoutTransition
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.ui.adapters.ProductImagesFullScreenAdapter
import kotlinx.android.synthetic.main.fragment_product_images.*

class ProductImagesFullScreenFragment : BaseFragment() {
    var images = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_product_images, container, false)
        return v
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val  animLinear = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        v?.setAnimation(animLinear);

        val linearLayoutManager = LinearLayoutManager(activity!!, RecyclerView.HORIZONTAL,false)
        product_images.layoutManager = linearLayoutManager

        val adapter = ProductImagesFullScreenAdapter(activity!!)
        adapter.images = images
        product_images.adapter = adapter
    }



}