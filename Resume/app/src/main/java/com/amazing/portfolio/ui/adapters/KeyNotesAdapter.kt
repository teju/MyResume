package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.Helper
import com.amazing.portfolio.model.featuresContent.KeyNotes
import com.amazing.portfolio.ui.adapters.KeyNotesAdapter.ReyclerViewHolder
import com.bumptech.glide.Glide
import java.util.*


class KeyNotesAdapter(
    context: Context) : RecyclerView.Adapter<ReyclerViewHolder>() {
    private var lastPosition = 0
    private val layoutInflater: LayoutInflater
    private val context: Context
    public var keynotesList = ArrayList<KeyNotes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerViewHolder {
        val item =
            layoutInflater.inflate(R.layout.item_keynotes_view, parent, false)
        return ReyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: ReyclerViewHolder, position: Int) {
        holder.tittle.text = keynotesList.get(position).title
        holder.description.text = Html.fromHtml(keynotesList.get(position).description)
        try {
            Glide.with(context)
                .load(keynotesList.get(position).image)
                .into(holder.image_view)
        } catch (e: Exception) {
        }
    }
    override fun getItemCount(): Int {
        return keynotesList.size
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val parent: FrameLayout
        val tittle: TextView
        val description: TextView
        val image_view: ImageView

        init {
            parent = v.findViewById<View>(R.id.parent) as FrameLayout
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