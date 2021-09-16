package com.amazing.portfolio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.callback.TabBarClickListener
import com.amazing.portfolio.ui.adapters.MyProjectsAdapter
import com.amazing.portfolio.ui.adapters.ViewPagerAdapter
import com.amazing.portfolio.ui.views.layoutmanager.BannerLayoutManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.feature_tab_item.*
import kotlinx.android.synthetic.main.fragment_features_tab.*


class FeaturesTabFragment : BaseFragment(), TabBarClickListener {
    private val MAX_LINES_COLLAPSED = 3
    private val navIcons = intArrayOf(
        R.drawable.features,
        R.drawable.keynote,
        R.drawable.works
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


        tab_tablayout.setupWithViewPager(viewpager)

        setupViewPager()
        setUpTAbs()
        tabbarClickListener = object  : TabBarClickListener {
            override fun onClicked() {
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
                LayoutInflater.from(activity!!).inflate(R.layout.feature_tab_item, null) as RelativeLayout

            // get child TextView and ImageView from this layout for the icon and label
            val tab_label =
                tab.findViewById<View>(R.id.nav_label) as TextView
            val tab_icon: ImageView = tab.findViewById<View>(R.id.nav_icon) as ImageView
            val nav_icon_bg: ImageView = tab.findViewById<View>(R.id.nav_icon_bg) as ImageView

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            tab_label.text = resources.getString(navLabels.get(i))
            tab_icon.setImageResource(navIcons.get(i))
            if (i == 0) {
                nav_icon_bg.visibility = View.VISIBLE
            } else {
                nav_icon_bg.visibility = View.GONE

            }
            // finally publish this custom view to navigation tab
            tab_tablayout.getTabAt(i)?.setCustomView(tab)
        }
        tab_tablayout.setOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val tabView = tab.customView
                    val nav_icon_bg = tabView!!.findViewById<View>(R.id.nav_icon_bg) as ImageView

                    nav_icon_bg.visibility = View.VISIBLE
                }

                // do as the above the opposite way to reset tab when state is changed
                // as it not the active one any more
                override fun onTabUnselected(tab: TabLayout.Tab) {
                    val tabView = tab.customView
                    val nav_icon_bg = tabView!!.findViewById<View>(R.id.nav_icon_bg) as ImageView
                    nav_icon_bg.visibility = View.GONE
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
        viewpager.setOnPageChangeListener(object  : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                /*if(position == 2) {
                    Handler().postDelayed({
                        home().toggle(false)
                    }, 100)
                    Handler().postDelayed({
                        home().toggle(true)

                    }, 1500)
                }*/
            }

            override fun onPageSelected(position: Int) {
                if(position == 2) {
                    Handler().postDelayed({
                        home().toggle(false)
                    }, 1000)
                    Handler().postDelayed({
                        home().toggle(true)

                    }, 2000)
                }
            }

        })
    }

}