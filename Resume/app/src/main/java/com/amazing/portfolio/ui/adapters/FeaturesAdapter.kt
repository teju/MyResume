package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.model.featuresContent.KeyNotes
import com.amazing.portfolio.ui.GlideApp
import com.amazing.portfolio.ui.adapters.FeaturesAdapter.ReyclerViewHolder
import java.util.*

class FeaturesAdapter(
    context: Context) : RecyclerView.Adapter<ReyclerViewHolder>() {
    private var lastPosition = 0
    private val layoutInflater: LayoutInflater
    private val context: Context
    public var keynotesList = ArrayList<KeyNotes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerViewHolder {
        val item =
            layoutInflater.inflate(R.layout.item_features_view, parent, false)
        return ReyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: ReyclerViewHolder, position: Int) {
        holder.tittle.text = keynotesList.get(position).title
        holder.description.text = keynotesList.get(position).description
        try {
            GlideApp.with(context)
                .load(keynotesList.get(position).image)
                .into(holder.image_view)
        } catch (e: Exception) {
        }
    }
    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(
                context,
                R.anim.list_enter
            )
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
    override fun getItemCount(): Int {
        return keynotesList.size
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val parent: RelativeLayout
        val cbg: CardView
        val tittle: TextView
        val description: TextView
        val image_view: ImageView

        init {
            parent = v.findViewById<View>(R.id.parent) as RelativeLayout
            cbg = v.findViewById<View>(R.id.cbg) as CardView
            tittle = v.findViewById<View>(R.id.tittle) as TextView
            description = v.findViewById<View>(R.id.description) as TextView
            image_view = v.findViewById<View>(R.id.image_view) as ImageView
        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}