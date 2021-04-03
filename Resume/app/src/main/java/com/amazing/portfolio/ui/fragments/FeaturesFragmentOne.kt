package com.amazing.portfolio.ui.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import com.amazing.portfolio.R
import com.amazing.portfolio.model.featuresContent.Features
import com.amazing.portfolio.ui.adapters.ImagePagerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_features_one.*
import kotlinx.android.synthetic.main.mobile_app_dev.*
import kotlinx.android.synthetic.main.remote_message.*


class FeaturesFragmentOne : BaseFragment() ,View.OnClickListener{
    private var imageAdapter: ImagePagerAdapter? = null
    var isExpanded = false
    val data  = ArrayList<String>()
    var databaseReference: DatabaseReference? = null
    var list: List<String> = ArrayList()
    val featuresList = ArrayList<Features>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_features_one, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        framelayoutone.setOnClickListener(this)
        framelayouttwo.setOnClickListener (this)
        framelayoutthree.setOnClickListener (this)
        imageAdapter = ImagePagerAdapter(activity)
        imageAdapter?.imageIdList = data
        viewPager.setAdapter(imageAdapter)
        viewPager.startAutoScroll()

        fetchData()
        ld.showLoadingV2()

    }
    fun fetchData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/contents")

        // Adding Add Value Event Listener to databaseReference.

        // Adding Add Value Event Listener to databaseReference.
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                featuresList.clear()
                ld.hide()
                for (postSnapshot in snapshot.children) {
                    val products : Features = postSnapshot.getValue(Features::class.java)!!
                    featuresList.add(products)
                }
                ld.showLoadingV2()
                fetchImage()
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

    }

    private fun initContents() {
        tvworks.text = featuresList.get(0).tittle
        tv_new_normal.text = featuresList.get(0).subtitle
        mExpandableTV.text = featuresList.get(0).content

        tv_mobile_app.text = featuresList.get(1).tittle
        tv_mobile_app_subtitle.text = featuresList.get(1).description
        mobile_app_dev.text = featuresList.get(1).content

        tv_have_idea.text = featuresList.get(2).tittle
        lets_start_subtitle.text = featuresList.get(2).subtitle
        lets_start.text = featuresList.get(2).content

        new_capablities.text = featuresList.get(3).tittle
        leads.text = featuresList.get(3).subtitle
        new_capibilities_desc.text = featuresList.get(3).content
    }

    fun fetchImage() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/scrollImages")

        // Adding Add Value Event Listener to databaseReference.

        // Adding Add Value Event Listener to databaseReference.
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ld.hide()
                for (postSnapshot in snapshot.children) {
                    data.add(postSnapshot.value.toString())
                }

                imageAdapter?.imageIdList = data
                imageAdapter?.notifyDataSetChanged()
                initContents()
            }

            override fun onCancelled(databaseError: DatabaseError) {

                // Hiding the progress dialog.
            }
        })

    }

    private fun expandTextView(tv : TextView) {
        val animation = ObjectAnimator.ofInt(tv, "maxLines", 10)
        animation.setDuration(100).start()
    }

    private fun collapseTextView(tv : TextView) {
        val animation = ObjectAnimator.ofInt(tv, "maxLines", 3)
        animation.setDuration(100).start()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.framelayoutone -> {
                if(!isExpanded) {
                    isExpanded = true
                    expandTextView(mExpandableTV)
                    collapseTextView(lets_start)
                    collapseTextView(mobile_app_dev)
                } else {
                    isExpanded = false
                    collapseTextView(mExpandableTV)
                }
                tabbarClickListener?.onClicked()
            }
            R.id.framelayouttwo -> {
                if(!isExpanded) {
                    isExpanded = true
                    collapseTextView(lets_start)
                    collapseTextView(mExpandableTV)
                    expandTextView(mobile_app_dev)
                } else {
                    isExpanded = false
                    collapseTextView(mobile_app_dev)
                }
                tabbarClickListener?.onClicked()
            }
            R.id.framelayoutthree -> {
                if(!isExpanded) {
                    isExpanded = true
                    expandTextView(lets_start)
                    collapseTextView(mobile_app_dev)
                    collapseTextView(mExpandableTV)

                } else {
                    isExpanded = false
                    collapseTextView(lets_start)
                }
                scrollView.postDelayed(Runnable { //replace this line to scroll up or down
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN)
                }, 900)

                tabbarClickListener?.onClicked()
            }
        }
    }


}