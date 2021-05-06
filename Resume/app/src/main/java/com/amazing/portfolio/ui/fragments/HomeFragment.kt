package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.FOCUS_DOWN
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import com.amazing.portfolio.R
import com.amazing.portfolio.ui.GlideApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.whatsapp.*
import java.util.*


class HomeFragment : BaseFragment() ,View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id) {

        }
    }
    var mDatas = ArrayList<String>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.home_fragment, container, false)
        return v
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ld.showLoadingV2()
        getImageList()
        whatapp()
    }
    fun initUI() {
        myScrollView.getViewTreeObserver()
            .addOnScrollChangedListener(OnScrollChangedListener {
                val scrollY: Int = myScrollView.getScrollY() // For ScrollView
                if(scrollY > 320 &&  scrollY < 500) {
                    val slideAnimation = AnimationUtils.loadAnimation(activity!!, R.anim.left_enter)
                    mobile_apps_dev.startAnimation(slideAnimation)
                    val slideAnimationRights = AnimationUtils.loadAnimation(activity!!, R.anim.right_enter)
                    tvmobile_apps_dev.startAnimation(slideAnimationRights)
                }
                if(scrollY > 520 &&  scrollY < 700) {
                    val slideAnimation = AnimationUtils.loadAnimation(activity!!, R.anim.left_enter)
                    we_build.startAnimation(slideAnimation)
                    we_build.setText(R.string.we_build)
                    val slideAnimationRights = AnimationUtils.loadAnimation(activity!!, R.anim.right_enter)
                    native_sdk_apps.startAnimation(slideAnimationRights)
                    native_sdk_apps.setImageResource(R.drawable.native_sdk_apps)
                }
                if(scrollY > 700 &&  scrollY < 900) {
                    val slideAnimation = AnimationUtils.loadAnimation(activity!!, R.anim.left_enter)
                    banner_four.startAnimation(slideAnimation)
                    banner_four.setImageResource(R.drawable.banner_four)
                }
                if(scrollY > 1000 &&  scrollY < 1200) {
                    val slideAnimationRights = AnimationUtils.loadAnimation(activity!!, R.anim.bottom_up)
                    lets_connect.startAnimation(slideAnimationRights)
                    lets_connect.setImageResource(R.drawable.lets_connect)
                }

            })

    }
    fun  getImageList() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("/home_page");

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                ld.hide()
                mDatas.clear()
                for (postSnapshot in dataSnapshot.children) {
                    mDatas.add(postSnapshot.value.toString())
                }
                initUI()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                ld.hide()

            }
        })

    }


}