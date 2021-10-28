package com.amazing.portfolio.ui.adapters

import android.R.attr.thumb
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.Helper
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.AppData
import com.amazing.portfolio.model.Products
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.app_detail_fragment.*
import kotlinx.android.synthetic.main.list_item.view.*


class ItemAdapter(val screenWidth:Int) : RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<AppData> = listOf()
    var context:Context? = null
    var itemClick : ItemClickListener?= null

    var firstCompletelyVisibleItemPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items.get(position))
        holder.itemView.setOnClickListener {
            itemClick?.onClickpos(position)

       }
//        val itemWidth = screenWidth / 3.33
//
//        val lp = holder.itemView.layoutParams
//        lp.height = lp.height
//        lp.width = itemWidth.toInt()
//        holder.itemView.layoutParams = lp

    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<AppData>) {
        items = newItems
        notifyDataSetChanged()
    }
}

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: AppData) {
        if(item.app_title.equals("more_items")) {
            view.more_items.visibility = View.VISIBLE
            view.list_item_background.visibility = View.GONE
        } else {
            view.more_items.visibility = View.GONE
            view.list_item_background.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(item.logo.trim())
                .into(view.list_item_icon)
            view.description.text = item.description.trim()
            view.tittle.text = Html.fromHtml(item.app_title.trim())
            view.llTop.getBackground()
                .setColorFilter(Color.parseColor(item?.app_colour), PorterDuff.Mode.SRC_ATOP);
            if (item.text_colour.equals("light", ignoreCase = true)) {
                view.description.setTextColor(itemView.context.resources.getColor(R.color.White))
                view.tittle.setTextColor(itemView.context.resources.getColor(R.color.White))
            } else {
                view.description.setTextColor(itemView.context.resources.getColor(R.color.Black))
                view.tittle.setTextColor(itemView.context.resources.getColor(R.color.Black))
            }
        }

    }
}