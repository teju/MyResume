package com.amazing.portfolio.ui.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.callback.NoInternetListener

class NoInternetDialogFragment : BaseDialogFragment() {


    companion object {
        val TAG = "NoInternetDialogFragment"
    }

    lateinit var listener: NoInternetListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.no_internet_dialog, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* btn_positive.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                dismiss()
                listener.let {
                    listener.onReloadClicked()
                }

            }
        })*/

    }

}