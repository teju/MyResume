package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.ui.adapters.MyProjectsAdapter
import kotlinx.android.synthetic.main.activity_rv.*
import java.util.*

/**
 * 矩阵实现弧形列表  RecyclerView
 */
class ProjectListFragment : BaseFragment() {
    private var mAdapter: MyProjectsAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.activity_rv, container, false)
        return v
    }
    var mDatas = ArrayList<String>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        rv.setLayoutManager(LinearLayoutManager(activity))
        rv.getViewTreeObserver().addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                findView()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rv.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                }
            }
        })
    }

    private fun initData() {
        if (mDatas == null) mDatas = ArrayList()
        for (i in 0..98) {
            mDatas!!.add("CAR_Item$i")
        }
    }

    private fun findView() {
        mAdapter = MyProjectsAdapter(activity!!,rv)
        mAdapter?.mDatas = mDatas
        rv.setAdapter(mAdapter)
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                for (i in 0 until recyclerView.childCount) {
                    recyclerView.getChildAt(i).invalidate()
                }
            }
        })
    }
}