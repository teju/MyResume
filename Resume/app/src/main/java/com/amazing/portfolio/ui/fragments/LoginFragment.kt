package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.amazing.portfolio.R
import com.amazing.portfolio.model.ProductArray
import com.amazing.portfolio.model.Products
import com.cunoraz.continuouscrollable.ContinuousScrollableImageView
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : BaseFragment() ,View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)
        return v
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skip.setOnClickListener(this)
        Handler().postDelayed({
            val image = ContinuousScrollableImageView(activity);
            image.setResourceId(R.drawable.sign_in_page);
            image.setDirection(ContinuousScrollableImageView.LEFT);
            image.setScaleType(ContinuousScrollableImageView.FIT_XY);
            image.setDuration(8000);
            flTop.addView(image)
        }, 3)
        val rootRef = FirebaseDatabase.getInstance().reference
        val namesRef = rootRef.child("myApps")
        val map: MutableMap<String, Products> = HashMap()
        var products = ProductArray()
        var product = Products()
        products.name = "Ezee"
        products.description = "SLIDE APP"
        products.image = "SLIDE APP"
        product.array.add(products)
        products = ProductArray()
        products.name = "Ezee"
        products.description = "Ezee APP"
        products.image = "Ezee APP"
        product.array.add(products)
        map.put("apps", product);
        println("map "+ Gson().toJson(map))
        namesRef.updateChildren(map as Map<String, Any>)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.skip ->{
                val mainFragment = MainFragment()
                home().setFragment(mainFragment)
            }
        }
    }
}