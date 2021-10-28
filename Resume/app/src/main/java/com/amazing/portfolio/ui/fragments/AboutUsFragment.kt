package com.amazing.portfolio.ui.fragments

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.portfolio.R
import com.amazing.portfolio.model.AboutUsData
import com.amazing.portfolio.ui.adapters.AboutUsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_about_us.*
import java.lang.Exception

class AboutUsFragment : BaseFragment() {
    private var aboutUsAdapter: AboutUsAdapter? = null
    val aboutusList = ArrayList<AboutUsData>()
    var isAnimated = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_us, container, false)
        return v
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity!!)
        recyclerView.layoutManager = linearLayoutManager
        aboutUsAdapter = AboutUsAdapter(activity!!)
        aboutUsAdapter?.aboutusList = ArrayList()
        recyclerView.adapter = aboutUsAdapter

        getAboutUSist()
        ld.showLoadingV2()
        whatapp(activity!!)
        animations()
        followUS()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        animations()
    }
    fun animations() {
        val animation = AnimationUtils.loadAnimation(
            context, R.anim.item_animation_fall_down
        )
        aboutus_1.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                ll_second.visibility = View.VISIBLE
                val ll_secondanimation = AnimationUtils.loadAnimation(
                    context, R.anim.item_animation_fall_down
                )
                ll_second.startAnimation(ll_secondanimation)
                ll_secondanimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        ll_third.visibility = View.VISIBLE
                        val ll_secondanimation = AnimationUtils.loadAnimation(
                            context, R.anim.item_animation_fall_down
                        )
                        ll_third.startAnimation(ll_secondanimation)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

    }

    fun  getAboutUSist() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("/aboutus");
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                aboutusList.clear()
                ld.hide()
                for (postSnapshot in dataSnapshot.children) {
                    val products: AboutUsData = postSnapshot.getValue(AboutUsData::class.java)!!
                    aboutusList.add(products)

                }
                aboutUsAdapter?.aboutusList = aboutusList
                aboutUsAdapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
            }
        })
        myScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            isViewVisible(recyclerView)
        }

    }
    fun followUS() {
        val i = Intent(Intent.ACTION_VIEW)

        try {
            var url = ""

            li.setOnClickListener {
                url = "https://www.linkedin.com/company/dappogee/"
                i.setData(Uri.parse(url));
                activity?.startActivity(i);
            }
            fb.setOnClickListener {
                url = "https://www.facebook.com/OfficialAppogee"
                i.setData(Uri.parse(url));
                activity?.startActivity(i);
            }
            insta.setOnClickListener {
                url = "https://www.instagram.com/officialappogee/"
                i.setData(Uri.parse(url));
                activity?.startActivity(i);
            }
            tw.setOnClickListener {
                url = "https://twitter.com/dAppOGee"
                i.setData(Uri.parse(url));
                activity?.startActivity(i);
            }

        }catch (e:Exception){

        }
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        myScrollView.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return if (scrollBounds.top < top) {
            if(!isAnimated) {
                aboutUsAdapter?.notifyDataSetChanged()
            }
            isAnimated = true
            true
        } else {
            false
        }
    }
}