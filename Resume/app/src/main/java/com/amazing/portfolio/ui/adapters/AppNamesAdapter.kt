package com.amazing.portfolio.ui.adapters

import android.R.attr.button
import android.content.Context
import android.content.res.ColorStateList
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
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.AboutUsData
import com.amazing.portfolio.model.AppData
import java.util.*


class AppNamesAdapter(
    context: Context) : RecyclerView.Adapter<AppNamesAdapter.ReyclerViewHolder>() {
    private val layoutInflater: LayoutInflater
    private val context: Context
    public var aboutusList = ArrayList<AppData>()
    var selectedPos = 0
    var itemClick : ItemClickListener?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerViewHolder {
        val item =
            layoutInflater.inflate(R.layout.item_app_names, parent, false)
        return ReyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: ReyclerViewHolder, position: Int) {
        holder.names.text = aboutusList.get(position).app_name
        if(selectedPos == position) {
            holder.names.setTextColor(context.resources.getColor(R.color.White))
            holder.names.setBackgroundTintList(ColorStateList.valueOf(context.resources.getColor(R.color.colorAccent)));
        } else {
            holder.names.setTextColor(context.resources.getColor(R.color.Black))
            holder.names.setBackgroundTintList(ColorStateList.valueOf(context.resources.getColor(R.color.soft_grey)));

        }
        holder.itemView.setOnClickListener {
            itemClick?.onClickpos(holder.layoutPosition)
        }
    }

    override fun getItemCount(): Int {
        return aboutusList.size
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val names: TextView


        init {
            names = v.findViewById<View>(R.id.names) as TextView


        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}