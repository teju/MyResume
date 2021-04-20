package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.model.AppData
import com.amazing.portfolio.model.Products
import com.amazing.portfolio.ui.adapters.AnimatedLayoutManager
import com.amazing.portfolio.ui.adapters.MyProjectsAdapter
import com.amazing.portfolio.ui.adapters.ProjectListsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_rv.*

/**
 * 矩阵实现弧形列表  RecyclerView
 */
class ProjectListFragment : BaseFragment() {
    private var projectsAdapter: ProjectListsAdapter? = null
    var runnable: Runnable? = null
    val handler = Handler()
    val appsList = ArrayList<Products>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.activity_rv, container, false)
        return v
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        projectLists()
        ld.showLoadingV2()
        getAppList()
    }


    fun projectLists() {
        val linearLayoutManager = AnimatedLayoutManager(activity!!)

        recycler_view.setLayoutManager(linearLayoutManager)
        recycler_view.updatePadding(top = 400)
        recycler_view.doOnLayout {
            (recycler_view.layoutManager as AnimatedLayoutManager).animateItemsIn()
        }
        projectsAdapter = ProjectListsAdapter(activity!!)
        projectsAdapter?.projectList = ArrayList()
        recycler_view.adapter = projectsAdapter
        autoScroll()
    }

    fun  getAppList() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("/myApps");

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                appsList.clear()
                ld.hide()
                for (postSnapshot in dataSnapshot.children) {
                    val products : Products = postSnapshot.getValue(Products::class.java)!!
                    appsList.add(products)

                }
                projectsAdapter?.projectList = appsList
                projectsAdapter?.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message

            }
        })

    }

    fun autoScroll() {
        try {
            runnable = object : Runnable {
                var count = 0
                override fun run() {
                    if (count == projectsAdapter?.getItemCount()) count = 0
                    if (count < projectsAdapter?.getItemCount()!!) {
                        recycler_view.smoothScrollToPosition(++count)
                        handler.postDelayed(this, 0)
                    }
                }
            }
            handler.postDelayed(runnable, 0)
        } catch (e:Exception){

        }
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }


}