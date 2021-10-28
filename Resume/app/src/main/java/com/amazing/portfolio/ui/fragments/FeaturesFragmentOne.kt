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
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.featuresContent.Features
import com.amazing.portfolio.ui.adapters.AboutUsAdapter
import com.amazing.portfolio.ui.adapters.FeaturesAdapter
import com.amazing.portfolio.ui.adapters.ImagePagerAdapter
import com.amazing.portfolio.ui.fragments.features_detail.FeatureOneFragmentDetail
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_features_one.*



class FeaturesFragmentOne : BaseFragment() ,View.OnClickListener{
    private var featureAdapter: FeaturesAdapter? = null
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

    override fun onHiddenChanged(hidden: Boolean) {
        featureAdapter?.notifyDataSetChanged()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity!!)
        recyclerView.layoutManager = linearLayoutManager
        featureAdapter = FeaturesAdapter(activity!!)
        featureAdapter?.featureList = ArrayList()
        featureAdapter?.itemClickListener = object :ItemClickListener {
            override fun onClickpos(pos: Int) {
                val feaDetailFragment = FeatureOneFragmentDetail()
                if(pos == 0) {
                    feaDetailFragment.url = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature1.png?alt=media&token=4fd8c68b-f354-403e-9426-555ae79e6394"
                }
                if(pos == 1) {
                    feaDetailFragment.url = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature2.png?alt=media&token=67493d6f-217f-43c7-bd3e-384379387be8"
                }
                if(pos == 2) {
                    feaDetailFragment.url = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature3.png?alt=media&token=68af12d7-b4b7-4873-a616-a5a566a87805"
                }
                if(pos == 3) {
                    feaDetailFragment.url = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature4.png?alt=media&token=be70b148-a6fe-4909-a82a-06d67a4ce598"
                }
                if(pos == 4) {
                    feaDetailFragment.url = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature5.png?alt=media&token=5f58b8e2-1bee-4929-9018-7e3d3eacdbaa"
                }
                home().setFragment(feaDetailFragment)
            }
        }
        recyclerView.adapter = featureAdapter
        whatapp(activity!!)
        ld.showLoadingV2()
        fetchImage()
    }

    fun fetchImage() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/scrollImages")

        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ld.hide()
                data.clear()
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