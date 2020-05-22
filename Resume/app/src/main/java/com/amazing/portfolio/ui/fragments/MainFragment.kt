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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(MAINFRAGMENT_LAYOUT, container, false)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initNavigationView()
        } catch (e : Exception){

        }
    }

    fun  initNavigationView() {
        bottomNavigation.add(MeowBottomNavigation.Model(FIRST_TAB, R.drawable.ic_launcher_foreground))
        bottomNavigation.add(MeowBottomNavigation.Model(SECOND_TAB, R.drawable.earth0))
        bottomNavigation.add(MeowBottomNavigation.Model(THIRD_TAB, R.drawable.ic_launcher_foreground))
        bottomNavigation.add(MeowBottomNavigation.Model(FOURTH_TAB, R.drawable.earth1))
        setCurrentItem(FIRST_TAB)
        bottomNavigation.setOnShowListener {
            setCurrentItem(it.id)

        }

        bottomNavigation.setOnClickMenuListener {
            setCurrentItem(it.id)

        }
    }


    fun setCurrentItem(which: Int) {

        if (which == FIRST_TAB) {
            currentTab = FIRST_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "FIRST_TAB",
                "MAIN_TAB", AppListFragment(), Helper.listFragmentsMainTab
            )
        } else if (which == SECOND_TAB) {
            currentTab = FIRST_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "SECOND_TAB",
                "MAIN_TAB", HomeFragment(), Helper.listFragmentsMainTab
            )

        } else if (which == THIRD_TAB) {
            currentTab = THIRD_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "THIRD_TAB",
                "MAIN_TAB", HomeFragment(), Helper.listFragmentsMainTab
            )
        } else if (which == FOURTH_TAB) {
            currentTab = FOURTH_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "FOURTH_TAB",
                "MAIN_TAB", HomeFragment(), Helper.listFragmentsMainTab
            )

        }
    }
    companion object {

        //region Constants Tab
        var FIRST_TAB = 0 //Homepage
        var SECOND_TAB = 1 //Pay
        var THIRD_TAB = 2 //De Era
        var FOURTH_TAB = 3 //Cart


    }

}
