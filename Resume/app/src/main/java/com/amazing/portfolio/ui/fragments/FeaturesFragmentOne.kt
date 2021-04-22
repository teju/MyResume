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

        mExpandableTV.setOnClickListener(this)
        mobile_app_dev.setOnClickListener (this)
        lets_start.setOnClickListener (this)
        imageAdapter = ImagePagerAdapter(activity)
        imageAdapter?.imageIdList = data
        viewPager.setAdapter(imageAdapter)
        viewPager.setSlideDuration(500)
        viewPager.startAutoScroll()

        fetchData()
        ld.showLoadingV2()

    }
    fun fetchData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/contents")

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
        readMoreLess(mExpandableTV,3)

        tv_mobile_app.text = featuresList.get(1).tittle
        tv_mobile_app_subtitle.text = featuresList.get(1).description
        mobile_app_dev.text = featuresList.get(1).content
        readMoreLess(mobile_app_dev,3)

        tv_have_idea.text = featuresList.get(2).tittle
        lets_start_subtitle.text = featuresList.get(2).subtitle
        lets_start.text = featuresList.get(2).content
        readMoreLess(lets_start,3)

        new_capablities.text = featuresList.get(3).tittle
        leads.text = featuresList.get(3).subtitle
        new_capibilities_desc.text = featuresList.get(3).content
    }

    fun fetchImage() {
        databaseReference = FirebaseDatabase.getInstance().getReference("features/scrollImages")

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
            }
        })

    }
    fun readMoreLess(tv : TextView,maxLine:Int) {
        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        var expandText = ". . . Read More"
        val vto = tv.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val text: String
                val lineEndIndex: Int
                val obs = tv.viewTreeObserver
                obs.removeGlobalOnLayoutListener(this)
                 if (maxLine > 0 && tv.lineCount >= maxLine) {
                    expandText = ". . . Read More"
                    lineEndIndex = tv.layout.getLineEnd(maxLine - 1)
                    text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                        .toString() + expandText
                } else {
                    expandText = "  Read Less"
                    lineEndIndex = tv.layout.getLineEnd(tv.layout.lineCount - 1)
                    text = tv.text.subSequence(0, lineEndIndex)
                        .toString() + expandText
                }
                tv.text = colorString(R.color.colorAccent,text,expandText)
                tv.movementMethod = LinkMovementMethod.getInstance()
            }
        })
    }
    fun colorString(
        color: Int,
        text: String,
        vararg wordsToColor: String
    ): SpannableString? {
        val coloredString = SpannableString(text)
        for (word in wordsToColor) {
            if (word.length != 1) {
                val startColorIndex = text.toLowerCase().indexOf(word.toLowerCase())
                val endColorIndex = startColorIndex + word.length
                try {
                    coloredString.setSpan(
                        ForegroundColorSpan(Color.parseColor("#EF6C00")), startColorIndex, endColorIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val bss = StyleSpan(Typeface.BOLD); // Span to make text bold

                    coloredString.setSpan(
                        bss, startColorIndex, endColorIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                } catch (e: Exception) {
                    e.message
                }
            } else {
                var start = 0
                for (token in text.split("[\u00A0 \n]".toRegex()).toTypedArray()) {
                    if (token.length > 0) {
                        start = text.indexOf(token, start)
                        // Log.e("tokentoken", "-token-" + token + "   --start--" + start);
                        val x = token.toLowerCase()[0]
                        val w = word.toLowerCase()[0]
                        // Log.e("tokentoken", "-w-" + w + "   --x--" + x);
                        if (x == w) {
                            // int startColorIndex = text.toLowerCase().indexOf(word.toLowerCase());
                            val endColorIndex = start + word.length
                            try {
                                coloredString.setSpan(
                                    ForegroundColorSpan(color), start, endColorIndex,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                                )
                            } catch (e: Exception) {
                                e.message
                            }
                        }
                    }
                }
            }
        }
        return coloredString
    }
    private fun expandTextView(tv : TextView) {
        val animation = ObjectAnimator.ofInt(tv, "maxLines", 1000)
        animation.setDuration(100).start()
        tv.setLayoutParams(tv.getLayoutParams());
        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
        tv.invalidate();
        readMoreLess(tv, -1);
    }

    private fun collapseTextView(tv : TextView) {
        val animation = ObjectAnimator.ofInt(tv, "maxLines", 3)
        animation.setDuration(100).start()
        tv.setLayoutParams(tv.getLayoutParams());
        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
        tv.invalidate();
        readMoreLess(tv, 3);
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.mExpandableTV -> {
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
            R.id.mobile_app_dev -> {
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
            R.id.lets_start -> {
                if(!isExpanded) {
                    isExpanded = true
                    expandTextView(lets_start)
                    collapseTextView(mobile_app_dev)
                    collapseTextView(mExpandableTV)

                } else {
                    isExpanded = false
                    collapseTextView(lets_start)
                }

                tabbarClickListener?.onClicked()
            }
        }
    }


}