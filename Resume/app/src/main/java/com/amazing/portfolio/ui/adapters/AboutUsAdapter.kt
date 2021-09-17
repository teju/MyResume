package com.amazing.portfolio.ui.adapters

import android.R.attr.button
import android.content.Context
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
import com.amazing.portfolio.model.AboutUsData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*


class AboutUsAdapter(
    context: Context) : RecyclerView.Adapter<AboutUsAdapter.ReyclerViewHolder>() {
    private val layoutInflater: LayoutInflater
    private val context: Context
    public var aboutusList = ArrayList<AboutUsData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerViewHolder {
        val item =
            layoutInflater.inflate(R.layout.item_about_us, parent, false)
        return ReyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: ReyclerViewHolder, position: Int) {
        if(position % 2 != 0) {
            val params = holder.rlprofile_pic.getLayoutParams() as RelativeLayout.LayoutParams
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            holder.rlprofile_pic.setLayoutParams(params)

            val deatilsparams = holder.deatils.getLayoutParams() as RelativeLayout.LayoutParams
            deatilsparams.addRule(RelativeLayout.LEFT_OF,R.id.rlprofile_pic)
            deatilsparams.addRule(RelativeLayout.RIGHT_OF,0)
            holder.deatils.setLayoutParams(deatilsparams)
        } else {
            val params = holder.rlprofile_pic.getLayoutParams() as RelativeLayout.LayoutParams
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            holder.rlprofile_pic.setLayoutParams(params)


            val deatilsparams = holder.deatils.getLayoutParams() as RelativeLayout.LayoutParams
            deatilsparams.addRule(RelativeLayout.LEFT_OF,0)
            deatilsparams.addRule(RelativeLayout.RIGHT_OF,R.id.rlprofile_pic)
            holder.deatils.setLayoutParams(deatilsparams)
        }
        holder.name.text = "Name : "+aboutusList.get(position).Name
        holder.description.text = aboutusList.get(position).Description
        holder.experience.text = "Experience : "+aboutusList.get(position).Experience
        holder.level.text = "Level : "+aboutusList.get(position).Level
        holder.role.text = aboutusList.get(position).Role
        setAnimation(holder.itemView,position)
        Glide.with(context)
            .load(aboutusList.get(position).image)
            .into(holder.profile_pic)

    }
    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position %2 ==  0) {
            val animation = AnimationUtils.loadAnimation(
                context,
                R.anim.left_enter
            )
            viewToAnimate.startAnimation(animation)
        } else {
            val animation = AnimationUtils.loadAnimation(
                context,
                R.anim.right_enter
            )
            viewToAnimate.startAnimation(animation)
        }
    }

    override fun getItemCount(): Int {
        return aboutusList.size
    }

    inner class ReyclerViewHolder(v: View) :
        RecyclerView.ViewHolder(v) {
        val profile_pic: ImageView
        val about_us_icon_dp: ImageView
        val deatils: LinearLayout
        val description: TextView
        val role: TextView
        val experience: TextView
        val name: TextView
        val level: TextView
        val rlprofile_pic: RelativeLayout


        init {
            profile_pic = v.findViewById<View>(R.id.profile_pic) as ImageView
            about_us_icon_dp = v.findViewById<View>(R.id.about_us_icon_dp) as ImageView
            deatils = v.findViewById<View>(R.id.deatils) as LinearLayout
            description = v.findViewById<View>(R.id.description) as TextView
            role = v.findViewById<View>(R.id.role) as TextView
            experience = v.findViewById<View>(R.id.experience) as TextView
            name = v.findViewById<View>(R.id.name) as TextView
            level = v.findViewById<View>(R.id.level) as TextView
            rlprofile_pic = v.findViewById<View>(R.id.rlprofile_pic) as RelativeLayout

        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}