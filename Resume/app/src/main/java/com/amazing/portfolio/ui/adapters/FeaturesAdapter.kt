package com.amazing.portfolio.ui.adapters

import android.R.attr.button
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.model.AboutUsData
import com.bumptech.glide.Glide
import java.util.*


class FeaturesAdapter(
    context: Context) : RecyclerView.Adapter<FeaturesAdapter.ReyclerViewHolder>() {
    private val layoutInflater: LayoutInflater
    private val context: Context
    public var featureList = ArrayList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerViewHolder {
        val item =
            layoutInflater.inflate(R.layout.item_features_view, parent, false)
        return ReyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: ReyclerViewHolder, position: Int) {
        Glide.with(context)
            .load(featureList.get(position))
            .into(holder.image_view)
    }

    override fun getItemCount(): Int {
        return featureList.size
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val image_view: ImageView


        init {
            image_view = v.findViewById<View>(R.id.image_view) as ImageView
        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}