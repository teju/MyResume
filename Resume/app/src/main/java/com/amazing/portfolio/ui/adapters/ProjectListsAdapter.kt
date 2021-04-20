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
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.model.featuresContent.KeyNotes
import com.amazing.portfolio.ui.GlideApp
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*

class ProjectListsAdapter(
    context: Context) : RecyclerView.Adapter<ProjectListsAdapter.ReyclerViewHolder>() {
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
        GlideApp.with(context)
            .load(projectList.get(position).logo)
            .into(holder.app_logo)
        holder.app_name.text = projectList.get(position).name
        holder.app_name.text = projectList.get(position).description
        holder.parent.setCardBackgroundColor(Color. parseColor(projectList.get(position).bg_colour))
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


        init {
            parent = v.findViewById<View>(R.id.parent) as CardView
            app_logo = v.findViewById<View>(R.id.app_logo) as ImageView
            app_name = v.findViewById<View>(R.id.app_name) as TextView
            tv_description = v.findViewById<View>(R.id.tv_description) as TextView

        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}