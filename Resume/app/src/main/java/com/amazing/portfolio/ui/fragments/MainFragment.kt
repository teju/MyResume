package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.Helper
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment() {

    val MAINFRAGMENT_LAYOUT = R.layout.main_fragment
    private var currentTab = FIRST_TAB
    var instance : Int = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(MAINFRAGMENT_LAYOUT, container, false)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initNavigationView()
        } catch (e : Exception){

        }
    }

    fun  initNavigationView() {
       bottomNavigation.apply {

            add(MeowBottomNavigation.Model(FIRST_TAB, R.drawable.app_logo))
            add(MeowBottomNavigation.Model(SECOND_TAB, R.drawable.app_logo))
            add(MeowBottomNavigation.Model(THIRD_TAB, R.drawable.app_logo))
            add(MeowBottomNavigation.Model(FOURTH_TAB, R.drawable.app_logo))
            add(MeowBottomNavigation.Model(FIFTH_TAB, R.drawable.app_logo))

            setCurrentItem(instance)
            showTab()
           setOnShowListener {
                setCurrentItem(it.id)

            }

            setOnClickMenuListener {
                setCurrentItem(it.id)

            }

            setOnReselectListener {
                setCurrentItem(it.id)

            }

        }



    }

    open fun showTab() {
        activity?.runOnUiThread(object:Runnable {
             override fun run() {
                 bottomNavigation.show(instance)
             }
        })
    }
    fun setCurrentItem(which: Int) {

        if (which == FIRST_TAB) {
            currentTab = FIRST_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "FIRST_TAB",
                "MAIN_TAB", HomeFragment(), Helper.listFragmentsMainTab
            )


        } else if (which == SECOND_TAB) {
            currentTab = FIRST_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "SECOND_TAB",
                "MAIN_TAB", AppListFragment(), Helper.listFragmentsMainTab
            )

        } else if (which == THIRD_TAB) {
            currentTab = THIRD_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "THIRD_TAB",
                "MAIN_TAB", MenuScreenFragment(false,this), Helper.listFragmentsMainTab
            )
        } else if (which == FOURTH_TAB) {
            currentTab = FOURTH_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "FOURTH_TAB",
                "MAIN_TAB", HomeFragment(), Helper.listFragmentsMainTab
            )

        }
       // bottomNavigation.show(which)
    }
    companion object {

        //region Constants Tab
        var FIRST_TAB = 0 //Homepage
        var SECOND_TAB = 1 //Pay
        var THIRD_TAB = 2 //De Era
        var FOURTH_TAB = 3 //Cart
        var FIFTH_TAB = 4 //Cart


    }

}
