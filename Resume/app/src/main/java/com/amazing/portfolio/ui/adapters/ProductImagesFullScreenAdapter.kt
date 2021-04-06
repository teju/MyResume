package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.ui.GlideApp
import kotlinx.android.synthetic.main.app_detail_fragment.*
import kotlinx.android.synthetic.main.products_image_itemview.view.*

class ProductImagesFullScreenAdapter(val context: Context) : RecyclerView.Adapter<ProductImagesFullScreenAdapter.ViewHolder>() {
    var images = ArrayList<String>()
    companion object {

        @kotlin.jvm.JvmField
        var BIG_SCALE = 1.5f
        @kotlin.jvm.JvmField
        var SMALL_SCALE = 0.7f
        @kotlin.jvm.JvmField
        var DIFF_SCALE = BIG_SCALE - SMALL_SCALE
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesFullScreenAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.products_image_itemview_full, parent, false)
        val v = ViewHolder(view)
        return v
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ProductImagesFullScreenAdapter.ViewHolder, position: Int) {
        GlideApp.with(context)
            .load(images.get(position))
            .into(holder.image_view)

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val image_view = view.image_view
    }
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

   
}