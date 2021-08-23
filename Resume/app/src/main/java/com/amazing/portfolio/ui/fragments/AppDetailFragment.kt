package com.amazing.portfolio.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazing.portfolio.R
import android.view.animation.AnimationUtils

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        //mDelayedTransactionHandler.postDelayed(mRunnable, 1000);
        val  animLinear = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        v?.setAnimation(animLinear);

        val linearLayoutManager = LinearLayoutManager(activity!!, RecyclerView.HORIZONTAL,false)
        rv_images.layoutManager = linearLayoutManager
        productImagesAdapter = ProductImagesAdapter(activity!!)
        rv_images.adapter = productImagesAdapter
        productImagesAdapter?.itemClick = object : ItemClickListener{
            override fun onClickpos(pos: Int) {
                val productImagesFullScreenFragment = ProductImagesFullScreenFragment()
                productImagesFullScreenFragment.images = products?.releated_images!!
                home().setFragment(productImagesFullScreenFragment)
            }

        }
        initData()
    }

    fun initData() {
        val slideAnimation = AnimationUtils.loadAnimation(activity!!, R.anim.left_enter)
        val slideAnimationRight = AnimationUtils.loadAnimation(activity!!, R.anim.right_enter)
        logo.startAnimation(slideAnimation)
        rv_images.startAnimation(slideAnimation)
        tv_description.startAnimation(slideAnimationRight)
        ratings.startAnimation(slideAnimationRight)

        productImagesAdapter?.images = products?.releated_images!!
        try {
            pagerlayout.setBackgroundColor(Color.parseColor(products?.bg_image))
        }catch (e:Exception){

        }
        Glide.with(activity!!)
            .load(products?.logo)
            .into(logo)
        tv_description.text = products?.description
        ratings.text = products?.ratings
    }
}
