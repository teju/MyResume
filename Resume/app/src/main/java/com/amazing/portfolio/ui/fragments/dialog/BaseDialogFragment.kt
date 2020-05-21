package com.amazing.portfolio.ui.fragments.dialog

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.UserInfoManager
import com.amazing.portfolio.ui.ActivityMain


open class BaseDialogFragment : DialogFragment() {

    var userInfo: UserInfoManager? = null
        private set

    var v: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.userInfo = UserInfoManager.getInstance(activity!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        v?.let {
           // setBackButtonToolbarStyleOne(v!!)
        }

        dialog?.let {
            it.window!!.setBackgroundDrawableResource(R.color.transparent)
            it.window!!.setBackgroundDrawable(InsetDrawable(ColorDrawable(Color.TRANSPARENT), 20))
        }


    }

    fun home(): ActivityMain {
        return activity as ActivityMain
    }

//    fun setBackButtonToolbarStyleOne(v: View) {
//        try {
//            val llBack = v.findViewById<LinearLayout>(R.id.llBack)
//            llBack.setOnClickListener { home().onBackPressed() }
//        } catch (e: Exception) {
//            Helper.logException(activity, e)
//        }
//
//    }
//
//    fun setBackButtonToolbarStyleTwo(v: View, clickListener: View.OnClickListener?) {
//        try {
//            val llBack = v.findViewById<LinearLayout>(R.id.llBack)
//            llBack.setOnClickListener { view -> clickListener?.onClick(view) }
//        } catch (e: Exception) {
//            Helper.logException(activity, e)
//        }
//    }

}