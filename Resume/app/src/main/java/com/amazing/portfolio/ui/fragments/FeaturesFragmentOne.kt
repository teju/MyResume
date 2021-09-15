package com.amazing.portfolio.ui.fragments

import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.amazing.portfolio.R
import com.amazing.portfolio.model.featuresContent.Features
import com.amazing.portfolio.ui.adapters.AboutUsAdapter
import com.amazing.portfolio.ui.adapters.FeaturesAdapter
import com.amazing.portfolio.ui.adapters.ImagePagerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_features_one.*



class FeaturesFragmentOne : BaseFragment() ,View.OnClickListener{
    private var featureAdapter: FeaturesAdapter? = null
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
        val linearLayoutManager = LinearLayoutManager(activity!!)
        recyclerView.layoutManager = linearLayoutManager
        featureAdapter = FeaturesAdapter(activity!!)
        featureAdapter?.featureList = ArrayList()
        recyclerView.adapter = featureAdapter
        whatapp()
        ld.showLoadingV2()
        fetchImage()
    }

    fun fetchImage() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/scrollImages")

        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ld.hide()
                for (postSnapshot in snapshot.children) {
                    data.add(postSnapshot.value.toString())
                }
                featureAdapter?.featureList = data
                featureAdapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

    }

    override fun onClick(v: View?) {

    }
}