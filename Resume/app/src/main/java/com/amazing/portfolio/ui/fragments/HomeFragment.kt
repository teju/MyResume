package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import com.amazing.portfolio.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : BaseFragment() ,View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id) {

        }
    }

    var mStorage: FirebaseStorage? = null
    val banners: ArrayList<Int> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.home_fragment, container, false)
        return v
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myScrollView.getViewTreeObserver()
            .addOnScrollChangedListener(OnScrollChangedListener {
                val scrollY: Int = myScrollView.getScrollY() // For ScrollView
                if(scrollY > 320 &&  scrollY < 500) {
                    val slideAnimation = AnimationUtils.loadAnimation(activity!!, R.anim.left_enter)
                    mobile_apps_dev.startAnimation(slideAnimation)
                    mobile_apps_dev.setImageResource(R.drawable.mobile_apps_dev)
                    val slideAnimationRights = AnimationUtils.loadAnimation(activity!!, R.anim.right_enter)
                    tvmobile_apps_dev.startAnimation(slideAnimationRights)
                    tvmobile_apps_dev.setText(activity!!.resources.getString(R.string.mobile_apps_dev))
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
                    super_user.startAnimation(slideAnimation)
                    project_managers.startAnimation(slideAnimation)
                    imgcloud_support.startAnimation(slideAnimation)
                    super_user.setImageResource(R.drawable.super_user)
                    imgcloud_support.setImageResource(R.drawable.cloud_support)
                    project_managers.setImageResource(R.drawable.project_managers)
                    val slideAnimationRights = AnimationUtils.loadAnimation(activity!!, R.anim.right_enter)
                    friendly_apps.startAnimation(slideAnimationRights)
                    icn_2.startAnimation(slideAnimationRights)
                    icn_1.startAnimation(slideAnimationRights)
                    friendly_apps.setImageResource(R.drawable.friendly_apps)
                    icn_1.setImageResource(R.drawable.icn_1)
                    icn_2.setImageResource(R.drawable.icn_2)

                    val slideAnimattxt = AnimationUtils.loadAnimation(activity!!, R.anim.bottom_up)
                    tv1.startAnimation(slideAnimattxt)
                    tv2.startAnimation(slideAnimattxt)
                    tv3.startAnimation(slideAnimattxt)
                    tv4.startAnimation(slideAnimattxt)

                    tv1.text = "Super User\nFriendly App’s"
                    tv2.text = "24/7 Cloud Support\non strict dead line"
                    tv3.text = "Experienced Driven"
                    tv4.text = "Project managers to\nenthusiastic newbies, we have experienced programmers"

                }
                if(scrollY > 1000 &&  scrollY < 1200) {
                    val slideAnimationRights = AnimationUtils.loadAnimation(activity!!, R.anim.bottom_up)
                    lets_connect.startAnimation(slideAnimationRights)
                    lets_connect.setImageResource(R.drawable.lets_connect)
                }
                if(scrollY > 1100 &&  scrollY < 1250) {
                    val slideAnimationRights = AnimationUtils.loadAnimation(activity!!, R.anim.left_enter)
                    llwhatsapp.startAnimation(slideAnimationRights)
                    whatsapp.setImageResource(R.drawable.whatsapp)
                    tvChat.setText("Need Help? Chat with us")
                }

            })
       // fetchData()
    }

/*
    private fun fetchData() {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.reference
        val getImage = databaseReference.child("dummy_image")

        getImage.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // getting a DataSnapshot for the location at the specified
                // relative path and getting in the link variable
                val link =
                    dataSnapshot.getValue(String::class.java)!!

                // loading that data into rImage
                // variable which is ImageView
                Picasso.get().load(link).into(imageview)
            }

            // this will called when any problem
            // occurs in getting data
            override fun onCancelled(databaseError: DatabaseError) {
                // we are showing that error message in toast
            }
        })
    }
*/

}