package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.Products
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.app_detail_fragment.*
import kotlinx.android.synthetic.main.products_image_itemview.view.*

class ProductImagesAdapter(val context: Context) : RecyclerView.Adapter<ProductImagesAdapter.ViewHolder>(),View.OnClickListener {
    var images = ArrayList<String>()
    var itemClick : ItemClickListener?  = null
    companion object {

        @kotlin.jvm.JvmField
        var BIG_SCALE = 1.5f
        @kotlin.jvm.JvmField
        var SMALL_SCALE = 0.7f
        @kotlin.jvm.JvmField
        var DIFF_SCALE = BIG_SCALE - SMALL_SCALE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.products_image_itemview, parent, false)
        val v = ViewHolder(view)
        return v
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ProductImagesAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(this)
        holder.itemView.tag = holder.position
        Glide.with(context)
            .load(images.get(position))
            .into(holder.image_view)

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val image_view = view.image_view
    }

    override fun onClick(p0: View?) {
        itemClick?.onClickpos(p0?.tag as Int)
    }


}