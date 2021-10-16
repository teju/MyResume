package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.graphics.Color
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
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.model.featuresContent.KeyNotes
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*
import java.lang.Exception
import java.util.*

class ProjectListsAdapter(
    context: Context,var itemClickListener: ItemClickListener) : RecyclerView.Adapter<ProjectListsAdapter.ReyclerViewHolder>() {
    private var lastPosition = 0
    private val layoutInflater: LayoutInflater
    private val context: Context
    public var projectList = ArrayList<Products>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerViewHolder {
        val item =
            layoutInflater.inflate(R.layout.item_project_list, parent, false)
        return ReyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: ReyclerViewHolder, position: Int) {
        try {
            Glide.with(context)
                .load(projectList.get(position).logo)
                .into(holder.app_logo)
            holder.app_name.text = projectList.get(position).app_name
            holder.tv_description.text = projectList.get(position).description
            holder.parent.setCardBackgroundColor(Color.parseColor(projectList.get(position).bg_colour))
            if (projectList.get(position).text_colour.contains("light")) {
                holder.app_name.setTextColor(context.resources.getColor(R.color.White))
                holder.tv_description.setTextColor(context.resources.getColor(R.color.White))
                holder.tv_platforn.setTextColor(context.resources.getColor(R.color.White))
            } else {
                holder.app_name.setTextColor(context.resources.getColor(R.color.Black))
                holder.tv_description.setTextColor(context.resources.getColor(R.color.Black))
                holder.tv_platforn.setTextColor(context.resources.getColor(R.color.Black))
            }
            holder.parent.setOnClickListener {
                itemClickListener.onClickpos(position)
            }
        } catch (e:Exception){

        }

    }
    override fun getItemCount(): Int {
        return projectList.size
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val parent: CardView
        val app_logo: ImageView
        val app_name: TextView
        val tv_description: TextView
        val tv_platforn: TextView


        init {
            parent = v.findViewById<View>(R.id.parent) as CardView
            app_logo = v.findViewById<View>(R.id.app_logo) as ImageView
            app_name = v.findViewById<View>(R.id.app_name) as TextView
            tv_description = v.findViewById<View>(R.id.tv_description) as TextView
            tv_platforn = v.findViewById<View>(R.id.tv_platforn) as TextView

        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}