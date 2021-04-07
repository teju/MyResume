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
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.model.featuresContent.KeyNotes
import com.amazing.portfolio.ui.GlideApp
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

    }
    override fun getItemCount(): Int {
        return 30
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val parent: RelativeLayout


        init {
            parent = v.findViewById<View>(R.id.parent) as RelativeLayout

        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}