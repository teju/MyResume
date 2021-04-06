package com.amazing.portfolio.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.Helper
import kotlinx.android.synthetic.main.adapter.view.*
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder
import kotlin.random.Random
import android.view.animation.AnimationUtils.loadAnimation
import android.system.Os.listen
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.animation.AnimationUtils
import android.system.Os.listen
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class ProductsAdapter(
    val itemClick: (position:Int) -> Unit,val context: Context) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    companion object {

        @kotlin.jvm.JvmField
        var BIG_SCALE = 1.5f
        @kotlin.jvm.JvmField
        var SMALL_SCALE = 0.7f
        @kotlin.jvm.JvmField
        var DIFF_SCALE = BIG_SCALE - SMALL_SCALE
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter, parent, false)
        val v = ViewHolder(view)
        return v
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {


    }



    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val cv = view.cv
    }
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

   
}