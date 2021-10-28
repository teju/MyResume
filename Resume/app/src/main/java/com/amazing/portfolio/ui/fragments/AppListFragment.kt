package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.CenterZoomLinearLayoutManager
import com.amazing.portfolio.etc.Constants
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.AppData
import com.amazing.portfolio.ui.adapters.ItemAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_app_list.*
import java.lang.Exception


class AppListFragment(var mainFragment: MainFragment) : BaseFragment() {
    private var itemAdapter: ItemAdapter? = null
    var TAG = "AppListFragment"
    val appsList = ArrayList<AppData>()
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
        getAppList()
        ld.showLoadingV2()
        initAppNames()
    }
    fun initAppNames() {
        val linearLayoutManageritem = CenterZoomLinearLayoutManager(activity!!,RecyclerView.HORIZONTAL,false)
        item_list.layoutManager = linearLayoutManageritem
        itemAdapter = ItemAdapter(item_list.width)
        itemAdapter?.context = activity!!
        itemAdapter?.setItems(appsList)
       /* aboutUsAdapter?.itemClick  = object :ItemClickListener {
            override fun onClickpos(pos: Int) {
                item_list.smoothScrollToPosition(pos)
            }
        }*/
        itemAdapter?.itemClick = object  : ItemClickListener {
            override fun onClickpos(pos: Int) {
                try {
                    if(!appsList.get(pos).app_title.equals("more_items")) {
                        val appDetailFragment = AppDetailFragment()
                        appDetailFragment.products = appsList.get(pos)
                        home().setFragment(appDetailFragment)
                    } else {
                        val projectListFragment = ProjectListFragment()
                        home().setFragment(projectListFragment)

                    }
                }catch (e:Exception){

                }
            }

        }
        item_list.adapter = itemAdapter
    }
    fun  getAppList() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("/AppDetails");

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                appsList.clear()
                ld.hide()
                for (postSnapshot in dataSnapshot.children) {
                    val products : AppData = postSnapshot.getValue(AppData::class.java)!!
                    appsList.add(products)
                    Log.d(TAG,"onDataChange "+postSnapshot.value)

                }
                val appData = AppData()
                appData.app_title = "more_items"
                appsList.add(appData)
                itemAdapter?.setItems(appsList)
                itemAdapter?.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d(TAG,"onCancelled "+databaseError)
            }
        })

    }
/*
    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: AppData ->
            item_list.smoothScrollToPosition(position)
            val appDetailFragment = AppDetailFragment()
            appDetailFragment.products = appsList.get(position)
            home().setFragment(appDetailFragment)
        }
    }
*/


}
data class Item(
    val title: String,
    @DrawableRes val icon: Int
)
