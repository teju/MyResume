package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.amazing.portfolio.R
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.model.ProductsArray
import com.amazing.portfolio.ui.adapters.ItemAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_app_list.*


class AppListFragment : BaseFragment() {
    var TAG = "AppListFragment"
    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            item_list.smoothScrollToPosition(position)
            val appDetailFragment = AppDetailFragment()
            home().setFragment(appDetailFragment)
        }
    }
    private val possibleItems = listOf(
        Item("Airplanes", R.drawable.ic_launcher_foreground),
        Item("Cars", R.drawable.ic_launcher_foreground),
        Item("Food", R.drawable.ic_launcher_foreground),
        Item("Gas", R.drawable.ic_launcher_foreground),
        Item("Home", R.drawable.ic_launcher_foreground)
    )
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_app_list, container, false)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun initUI() {
        val displayMetrics = DisplayMetrics()
        activity!!.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        item_list.initialize(itemAdapter)
        item_list.setViewsToChangeColor(listOf(R.id.list_item_background,R.id.list_item_icon))
        itemAdapter.context = activity!!
        itemAdapter.setItems(getLargeListOfItems())
        getAppList()

    }
    fun  getAppList() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("/myApps");

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val appsList = ArrayList<Products>()
                for (postSnapshot in dataSnapshot.children) {
                    val products : Products = postSnapshot.getValue(Products::class.java)!!
                    appsList.add(products)
                }
                Log.d(TAG,"onDataChange "+appsList.size)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d(TAG,"onCancelled "+databaseError)
            }
        })

    }
    private fun getLargeListOfItems(): List<Item> {
        val items = mutableListOf<Item>()
        (0..40).map { items.add(possibleItems.random()) }
        return items
    }
}
data class Item(
    val title: String,
    @DrawableRes val icon: Int
)