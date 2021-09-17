package com.amazing.portfolio.ui.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazing.portfolio.R
import android.view.animation.AnimationUtils

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.etc.CenterZoomLinearLayoutManager
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.AppData
import com.amazing.portfolio.ui.adapters.ProductImagesAdapter
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.app_detail_fragment.*
import java.lang.Exception

class AppDetailFragment : BaseFragment() {

    private var productImagesAdapter: ProductImagesAdapter? = null
    public var products: AppData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.app_detail_fragment, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mDelayedTransactionHandler.postDelayed(mRunnable, 1000);
        val  animLinear = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_up);
        v?.setAnimation(animLinear);
        val linearLayoutManageritem = LinearLayoutManager(activity!!,RecyclerView.HORIZONTAL,false)
        rv_images.layoutManager = linearLayoutManageritem
        productImagesAdapter = ProductImagesAdapter(activity!!)
        rv_images.adapter = productImagesAdapter
        productImagesAdapter?.itemClick = object : ItemClickListener{
            override fun onClickpos(pos: Int) {
                val productImagesFullScreenFragment = ProductImagesFullScreenFragment()
                productImagesFullScreenFragment.images = products?.releated_images!!
                productImagesFullScreenFragment.app_name = products?.app_name!!
                home().setFragment(productImagesFullScreenFragment)
            }

        }
        initData()
    }

    fun initData() {
        productImagesAdapter?.images = products?.releated_images!!
        Glide.with(activity!!)
            .load(products?.logo)
            .into(app_image)
        tv_description.text = products?.description
        tv_tittle.text = products?.app_title
        tvgallery.text = products?.app_name+" Gallery"
        flTop.getBackground().setColorFilter(Color.parseColor( products?.app_colour), PorterDuff.Mode.SRC_ATOP);
    }
}
