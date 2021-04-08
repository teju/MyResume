package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.callback.TabBarClickListener
import com.amazing.portfolio.ui.adapters.MyProjectsAdapter
import com.amazing.portfolio.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_features_tab.*


class FeaturesTabFragment : BaseFragment(), TabBarClickListener {
    private val MAX_LINES_COLLAPSED = 3
    private val navIcons = intArrayOf(
        R.drawable.order_alphabetical_ascending,
        R.drawable.account_key,
        R.drawable.cellphone_text
    )
    private var mAdapter: MyProjectsAdapter? = null
    var mDatas = ArrayList<String>()

    private val navLabels = intArrayOf(
        R.string.features,
        R.string.keynote,
        R.string.works
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_features_tab, container, false)
        return v

    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSideView()
        tab_tablayout.setupWithViewPager(viewpager)

        setupViewPager()
        setUpTAbs()
        tabbarClickListener = object  : TabBarClickListener {
            override fun onClicked() {
                if(isAppBArExpanded(app_bar!!)) {
                    app_bar?.setExpanded(false)
                } else {
                    app_bar?.setExpanded(true)

                }
            }
        }

    }


    override fun onClicked() {

    }

    fun setUpTAbs() {
        for (i in 0 until tab_tablayout.getTabCount()) {
            // inflate the Parent LinearLayout Container for the tab
            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
            val tab =
                LayoutInflater.from(activity!!).inflate(R.layout.feature_tab_item, null) as LinearLayout

            // get child TextView and ImageView from this layout for the icon and label
            val tab_label =
                tab.findViewById<View>(R.id.nav_label) as TextView
            val tab_icon: ImageView =
                tab.findViewById<View>(R.id.nav_icon) as ImageView

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            tab_label.text = resources.getString(navLabels.get(i))

            // set the home to be active at first
            if (i == 0) {
                tab_label.setTextColor(resources.getColor(R.color.White))
                tab_icon.setColorFilter(ContextCompat.getColor(activity!!, R.color.White), android.graphics.PorterDuff.Mode.SRC_IN);

            } else {
                tab_icon.setColorFilter(ContextCompat.getColor(activity!!, R.color.Black), android.graphics.PorterDuff.Mode.SRC_IN);
                tab_label.setTextColor(resources.getColor(R.color.Black))

            }
            tab_icon.setImageResource(navIcons.get(i))

            // finally publish this custom view to navigation tab
            tab_tablayout.getTabAt(i)?.setCustomView(tab)
        }
        tab_tablayout.setOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {

                    // 1. get the custom View you've added
                    val tabView = tab.customView

                    // get inflated children Views the icon and the label by their id
                    val tab_label =
                        tabView!!.findViewById<View>(R.id.nav_label) as TextView
                    val tab_icon =
                        tabView!!.findViewById<View>(R.id.nav_icon) as ImageView

                    // change the label color, by getting the color resource value
                    tab_icon.setColorFilter(ContextCompat.getColor(activity!!, R.color.White), android.graphics.PorterDuff.Mode.SRC_IN);
                    tab_label.setTextColor(resources.getColor(R.color.White))
                    // change the image Resource
                    // i defined all icons in an array ordered in order of tabs appearances
                    // call tab.getPosition() to get active tab index.
                    tab_icon.setImageResource(navIcons[tab.position])
                }

                // do as the above the opposite way to reset tab when state is changed
                // as it not the active one any more
                override fun onTabUnselected(tab: TabLayout.Tab) {
                    val tabView = tab.customView
                    val tab_label =
                        tabView!!.findViewById<View>(R.id.nav_label) as TextView
                    val tab_icon =
                        tabView!!.findViewById<View>(R.id.nav_icon) as ImageView

                    // back to the black color
                    tab_label.setTextColor(resources.getColor(R.color.Black))
                    // and the icon resouce to the old black image
                    // also via array that holds the icon resources in order
                    // and get the one of this tab's position
                    tab_icon.setImageResource(navIcons[tab.position])
                    tab_icon.setColorFilter(ContextCompat.getColor(activity!!, R.color.Black), android.graphics.PorterDuff.Mode.SRC_IN);

                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                }
            }
        )
    }
    fun setupViewPager() {
        var adapter = ViewPagerAdapter(activity?.supportFragmentManager!!)

        // LoginFragment is the name of Fragment and the Login
        // is a title of tab
        adapter.addFragment(FeaturesFragmentOne(), "Features one")
        adapter.addFragment(FeaturesFragmentTwo(), "Features two")
        adapter.addFragment(ProjectListFragment(), "Features three")

        // setting adapter to view pager.
        viewpager.setAdapter(adapter)
    }
    fun initSideView() {
        initData()

        rv.setLayoutManager(LinearLayoutManager(activity))
        rv.setClipToPadding(false);
        rv.setClipChildren(false);
        sideView()
        arrow_right_drop_circle.setOnClickListener {
            toggle(left_project_list.isVisible)
        }
    }
    private fun toggle(show: Boolean) {
        if(show) {
            left_project_list.visibility = View.GONE
            arrow_right_drop_circle.animate().rotation(360f).setInterpolator(LinearInterpolator()).setDuration(500)
        } else {
            left_project_list.visibility = View.VISIBLE
            arrow_right_drop_circle.animate().rotation(-180f).setInterpolator(LinearInterpolator()).setDuration(500)

        }
    }

    fun sideView() {

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
    private fun initData() {
        if (mDatas == null) mDatas = java.util.ArrayList()
        for (i in 0..98) {
            mDatas!!.add("CAR_Item$i")
        }
    }
}