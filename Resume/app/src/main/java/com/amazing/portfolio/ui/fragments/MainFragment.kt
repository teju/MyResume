package com.amazing.portfolio.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazing.portfolio.R
import com.amazing.portfolio.etc.Helper
import com.amazing.portfolio.etc.callback.NotifyListener
import com.amazing.portfolio.ui.fragments.dialog.NotifyDialogFragment
import kotlinx.android.synthetic.main.main_fragment.*
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainFragment : BaseFragment() {

    val MAINFRAGMENT_LAYOUT = R.layout.main_fragment
    private var currentTab = FIRST_TAB
    var instance : Int = 0

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

        val menuItems = arrayOf(
            CbnMenuItem(
                R.string.home,
                R.drawable.ic_home, // the icon
                R.drawable.avd_home,// the AVD that will be shown in FAB
                FIRST_TAB // optional if you use Jetpack Navigation
            ),
            CbnMenuItem(
                R.string.features,
                R.drawable.ic_features,
                R.drawable.avd_features,
                SECOND_TAB
            ),
            CbnMenuItem(
                0,
                R.drawable.ic_menu,
                R.drawable.ic_menu,
                THIRD_TAB
            ),
            CbnMenuItem(
                R.string.works,
                R.drawable.ic_work,
                R.drawable.avd_work,
                FOURTH_TAB
            ),
            CbnMenuItem(
                R.string.aboutus,
                R.drawable.icn_about_us,
                R.drawable.avd_about_us,
                FIFTH_TAB
            )
        )

        nav_view.setMenuItems(menuItems, instance)
        setCurrentItem(instance)
        nav_view.setOnMenuItemClickListener { item, _ ->
            setCurrentItem(item.destinationId)
        }


    }

    open fun showTab() {
        activity?.runOnUiThread(object:Runnable {
             override fun run() {
                 nav_view.onMenuItemClick(instance)
                 setCurrentItem(instance)

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
                "MAIN_TAB",
                FeaturesTabFragment(), Helper.listFragmentsMainTab
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
                "MAIN_TAB", AppListFragment(), Helper.listFragmentsMainTab
            )

        } else if (which == FIFTH_TAB) {
            currentTab = FIFTH_TAB

            home().setOrShowExistingFragmentByTag(
                R.id.mainLayoutFragment, "FIFTH_TAB",
                "MAIN_TAB", AboutUsFragment(), Helper.listFragmentsMainTab
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
