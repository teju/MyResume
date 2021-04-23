package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.callback.ItemClickListener
import com.amazing.portfolio.model.AppData
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.ui.adapters.AppNamesAdapter
import com.amazing.portfolio.ui.adapters.ItemAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_app_list.*


class AppListFragment : BaseFragment() {
    private var aboutUsAdapter: AppNamesAdapter? = null
    var TAG = "AppListFragment"
    val appsList = ArrayList<AppData>()
    private var products: ArrayList<AppData> = ArrayList()

    private val possibleItems = listOf(
        Products()
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
        itemAdapter.setItems(appsList)
        getAppList()
        ld.showLoadingV2()
        item_list.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val myLayoutManager:LinearLayoutManager = item_list.getLayoutManager() as LinearLayoutManager
                val firstVisibleItemCount = myLayoutManager.findFirstVisibleItemPosition()
                val lastVisibleItemPosition = myLayoutManager.findLastVisibleItemPosition()
                aboutUsAdapter?.selectedPos = firstVisibleItemCount
                aboutUsAdapter?.notifyDataSetChanged()
                try {
                    if (dx > 0) {
                        rvnames.scrollToPosition(firstVisibleItemCount + 2)
                    } else if (dx < 0) {
                        rvnames.scrollToPosition(firstVisibleItemCount - 2)
                    }
                } catch (e:Exception) {

                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
    fun initAppNames() {
        val linearLayoutManager = LinearLayoutManager(activity!!,RecyclerView.HORIZONTAL,false)
        rvnames.layoutManager = linearLayoutManager
        aboutUsAdapter = AppNamesAdapter(activity!!)
        aboutUsAdapter?.aboutusList = appsList
        aboutUsAdapter?.itemClick  = object :ItemClickListener {
            override fun onClickpos(pos: Int) {
                item_list.smoothScrollToPosition(pos)
            }
        }
        rvnames.adapter = aboutUsAdapter

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
                itemAdapter.setItems(appsList)
                itemAdapter.notifyDataSetChanged()
                initAppNames()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d(TAG,"onCancelled "+databaseError)
            }
        })

    }
    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: AppData ->
            item_list.smoothScrollToPosition(position)
            val appDetailFragment = AppDetailFragment()
            appDetailFragment.products = appsList.get(position)
            home().setFragment(appDetailFragment)
        }
    }


}
data class Item(
    val title: String,
    @DrawableRes val icon: Int
)
