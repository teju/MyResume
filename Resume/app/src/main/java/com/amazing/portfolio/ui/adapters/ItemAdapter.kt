package com.amazing.portfolio.ui.adapters

import android.R.attr.thumb
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.Helper
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.ui.GlideApp
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.list_item.view.*


class ItemAdapter(val itemClick: (position:Int,item:Products) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Products> = listOf()
    var context:Context? = null
    var firstCompletelyVisibleItemPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items.get(position))
        holder.itemView.setOnClickListener {
            itemClick(position,items.get(position))
        }
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(Helper.dpToPx(context!!, 35), 0, Helper.dpToPx(context!!, 15), 0)
       // holder.itemView!!.setLayoutParams(params)
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<Products>) {
        items = newItems
        notifyDataSetChanged()
    }
}

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val storage = FirebaseStorage.getInstance()

    fun bind(item: Products) {
        view.list_item_icon.setImageResource(R.drawable.ic_launcher_background)
        GlideApp.with(itemView.context)
            .load(item.image.trim())
            .into(view.list_item_icon)


    }
}