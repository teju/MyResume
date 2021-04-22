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
            val params = holder.profile_pic.getLayoutParams() as RelativeLayout.LayoutParams
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            holder.profile_pic.setLayoutParams(params)

            val deatilsparams = holder.deatils.getLayoutParams() as RelativeLayout.LayoutParams
            deatilsparams.addRule(RelativeLayout.LEFT_OF,R.id.profile_pic)
            deatilsparams.addRule(RelativeLayout.RIGHT_OF,0)
            holder.deatils.setLayoutParams(deatilsparams)
        }
        holder.name.text = aboutusList.get(position).Name
        holder.description.text = aboutusList.get(position).description
        holder.education.text = "Education : "+aboutusList.get(position).education
        holder.experience.text = "Experience : "+aboutusList.get(position).experience
        holder.role.text = "Role : "+aboutusList.get(position).role
        setAnimation(holder.itemView,position)
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
        val deatils: LinearLayout
        val description: TextView
        val education: TextView
        val role: TextView
        val experience: TextView
        val name: TextView


        init {
            profile_pic = v.findViewById<View>(R.id.profile_pic) as ImageView
            deatils = v.findViewById<View>(R.id.deatils) as LinearLayout
            description = v.findViewById<View>(R.id.description) as TextView
            education = v.findViewById<View>(R.id.education) as TextView
            role = v.findViewById<View>(R.id.role) as TextView
            experience = v.findViewById<View>(R.id.experience) as TextView
            name = v.findViewById<View>(R.id.name) as TextView

        }
    }

    init {
        layoutInflater = LayoutInflater.from(context)
        this.context = context
    }
}