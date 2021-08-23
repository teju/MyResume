package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.featuresContent.KeyNotesICons
import com.bumptech.glide.Glide
import java.util.*

class FeaturesIconsAdapter(
    context: Context) : RecyclerView.Adapter<FeaturesIconsAdapter.ReyclerViewHolder>() {
    public var selectedPosition = 0
    private val layoutInflater: LayoutInflater
    private val context: Context
    var itemClickListener:ItemClickListener? = null
    public var keynotesList = ArrayList<KeyNotesICons>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerViewHolder {
        val item =
            layoutInflater.inflate(R.layout.item_features_icons_view, parent, false)
        return ReyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: ReyclerViewHolder, position: Int) {
        //holder.parent.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.list_enter)
        //holder.cbg.setBackgroundResource(R.drawable.peach_gradient);
        var imageURl = keynotesList.get(position).unselected

        if(position == selectedPosition) {
            imageURl = keynotesList.get(position).selected
        } else{
            imageURl = keynotesList.get(position).unselected
        }

        try {
            Glide.with(context)
                .load(imageURl)
                .into(holder.icons)
        } catch (e: Exception) {
        }
        holder.icons.setOnClickListener {
            itemClickListener?.onClickpos(holder.layoutPosition)
            selectedPosition = holder.layoutPosition
        }
    }
    override fun getItemCount(): Int {
        return keynotesList.size
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val icons: ImageView

        init {
            icons = v.findViewById<View>(R.id.icons) as ImageView
        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}