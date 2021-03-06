package com.appogee.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.appogee.R
import kotlinx.android.synthetic.main.home_fragment.*
import androidx.recyclerview.widget.GridLayoutManager
import com.appogee.model.Products
import com.appogee.ui.adapters.ProductsAdapter
import com.appogee.ui.views.BannerLayout
import com.appogee.ui.views.SpacesItemDecoration


class HomePageFragment : BaseFragment() {

    val banners: ArrayList<Int> = ArrayList()
    val productsarr: ArrayList<Products> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.home_fragment, container, false)
        return v
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}