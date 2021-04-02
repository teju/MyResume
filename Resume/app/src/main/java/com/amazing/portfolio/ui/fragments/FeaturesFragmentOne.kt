package com.amazing.portfolio.ui.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import com.amazing.portfolio.R
import com.amazing.portfolio.ui.adapters.ImagePagerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_features_one.*
import kotlinx.android.synthetic.main.mobile_app_dev.*
import kotlinx.android.synthetic.main.remote_message.*


class FeaturesFragmentOne : BaseFragment() {
    private var imageAdapter: ImagePagerAdapter? = null
    var isExpanded = false
    val data  = ArrayList<String>()
    var databaseReference: DatabaseReference? = null
    var list: List<String> = ArrayList()

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

        framelayoutone.setOnClickListener {
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
        framelayouttwo.setOnClickListener {
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
        framelayoutthree.setOnClickListener {
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
        imageAdapter = ImagePagerAdapter(activity)
        imageAdapter?.imageIdList = data
        viewPager.setAdapter(imageAdapter)
        viewPager.startAutoScroll()
        fetchImage()

    }
    fun fetchImage() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features")

        // Adding Add Value Event Listener to databaseReference.

        // Adding Add Value Event Listener to databaseReference.
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {

                    data.add(postSnapshot.value.toString())
                }

                imageAdapter?.imageIdList = data
                imageAdapter?.notifyDataSetChanged()
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


}